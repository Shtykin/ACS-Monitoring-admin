package ru.eshtykin.acs_monitoring_admin.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner
import ru.eshtykin.acs_monitoring_admin.domain.entity.User
import ru.eshtykin.acs_monitoring_admin.domain.usecase.*
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState
import ru.eshtykin.acs_monitoring_admin.settings.AuthStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addOwnerUseCase: AddOwnerUseCase,
    private val authAdminUseCase: AuthAdminUseCase,
    private val changeRoleUseCase: ChangeRoleUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val authStore: AuthStore
): ViewModel() {

    private val _uiState = mutableStateOf<ScreenState>(ScreenState.LoginScreen(LoginScreenState.UnAuthorized()))
    val uiState: State<ScreenState>
        get() = _uiState

    suspend fun authenticateAdmin(login: String, password: String): Boolean {
        _uiState.value = ScreenState.LoginScreen(LoginScreenState.Loading)
        return try {
            authStore.accessToken = authAdminUseCase.execute(login, password)
            _uiState.value = ScreenState.LoginScreen(LoginScreenState.Authorized)
            true
        } catch (e: Exception) {
            _uiState.value = ScreenState.LoginScreen(LoginScreenState.UnAuthorized(e.message))
            false
        }
    }
    fun unAuthenticateAdmin() {
        authStore.clearCredentials()
        _uiState.value = ScreenState.LoginScreen(LoginScreenState.UnAuthorized())
    }

    fun isAuthenticated(): Boolean {
        return authStore.isAuthenticated()
    }

    fun getUser(login: String){
        _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Loading)
        viewModelScope.launch {
            try {
                val user = getUserUseCase.execute(login)
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Details(user))
            } catch (e: Exception) {
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Error(e.message))
            }
        }
    }

    fun getAllUsers(){
        _uiState.value = ScreenState.UsersScreen(UsersScreenState.Loading)
        viewModelScope.launch {
            try {
                val users = getAllUsersUseCase.execute()
                _uiState.value = ScreenState.UsersScreen(UsersScreenState.Users(users))
            } catch (e: Exception) {
                _uiState.value = ScreenState.UsersScreen(UsersScreenState.Error(e.message))
            }
        }
    }

    fun changeUserRole(user: User, role: String) {
        viewModelScope.launch {
            try {
                val result = changeRoleUseCase.execute(user, role)
                if (result) {
                    val newUser = user.copy(role = role)
                    _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Details(newUser))
                } else {
                    throw Exception("Failed, result: $result")
                }
            } catch (e: Exception) {
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Error(e.message))
            }
        }
    }

    fun addUserOwner(user: User, owner: Owner) {
        viewModelScope.launch {
            try {
                val owner = addOwnerUseCase.execute(user, owner)
                if (owner.value.isNotEmpty()) {
                    val owners = user.owners?.toMutableList() ?: mutableListOf()
                    owners.add(Owner(owner.value))
                    val newUser = user.copy(owners = owners)
                    _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Details(newUser))
                } else {
                    throw Exception("Failed, owner: ${owner.value}")
                }
            } catch (e: Exception) {
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Error(e.message))
            }
        }
    }

    fun openRoleDialog(user: User){
        _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.RoleDialog(user))
    }

    fun openOwnerDialog(user: User){
        _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.OwnerDialog(user))
    }


}