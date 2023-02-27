package ru.eshtykin.acs_monitoring_admin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.eshtykin.acs_monitoring_admin.domain.Owner
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Acs_monitoringadminTheme {
                val viewModel: MainViewModel = viewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owners = mutableListOf<Owner>()
                    repeat(50) {
                        owners.add(Owner("Owner $it"))
                    }
                    DetailsScreen(DetailsScreenState.Details(User(login = "login 5", role = "Device", owners = owners)))

//                    LoginScreen(LoginScreenState.Loading)
//                    UsersScreen(uiState = UsersScreenState.Error("Empty users list"))

//                    val users = mutableListOf<User>()
//                    repeat(5) {
//                        users.add(User("login $it"))
//                    }
//                    val owners = mutableListOf<Owner>()
//                    repeat(10) {
//                        owners.add(Owner("Owner $it"))
//                    }
//                    users.add(User("login 5", role = "Explorer", owners = owners))
//                    repeat(5) {
//                        users.add(User("login 1$it", role = "Device"))
//                    }
//                    UsersScreen(UsersScreenState.Users(users))
                }
            }
        }
    }
}



