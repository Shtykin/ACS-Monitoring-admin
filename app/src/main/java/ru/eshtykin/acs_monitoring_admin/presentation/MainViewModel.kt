package ru.eshtykin.acs_monitoring_admin.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.eshtykin.acs_monitoring_admin.data.repository.RepositoryImpl
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState

class MainViewModel(application: Application): AndroidViewModel(application) {

    val repository = RepositoryImpl()

    private val _uiState = mutableStateOf<ScreenState>(ScreenState.LoginScreen(LoginScreenState.Loading))
    val uiState: State<ScreenState>
        get() = _uiState
    init {
        viewModelScope.launch {
            try {
//                Log.e("DEBUG", repository.checkServer())
//            val response = repository.authAdmin("admin", "adminpass")
//            val response = repository.getAllUsers()
                val response = repository.getUser("user11")
//            val response = repository.changeRole("user1", "Explorer")
//            val response = repository.addOwner("user1", "ЗРУ-01_№010123")
                Log.e("DEBUG", "response = $response")
            } catch (e: Exception){
                Log.e("DEBUG", "${e.message}")
            }
        }

    }

    fun getUser(login: String){
        _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Loading)
        viewModelScope.launch {
            try {
                val user = repository.getUser(login)
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Details(user))
            } catch (e: Exception) {
                _uiState.value = ScreenState.DetailsScreen(DetailsScreenState.Error(e.message))
            }
        }
    }
}