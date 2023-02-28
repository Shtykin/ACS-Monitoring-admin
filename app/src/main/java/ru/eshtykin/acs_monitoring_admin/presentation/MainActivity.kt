package ru.eshtykin.acs_monitoring_admin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ru.eshtykin.acs_monitoring_admin.domain.Owner
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.navigation.AppNavGraph
import ru.eshtykin.acs_monitoring_admin.navigation.Screen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Acs_monitoringadminTheme {
                val viewModel: MainViewModel = viewModel()
                val navHostController = rememberNavController()
                val uiState by viewModel.uiState
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavGraph(
                        navHostController = navHostController,
                        loginScreenContent = {
                            LoginScreen(
                                uiState = uiState,
                                onLoginClick = { login, pass ->
                                    if (viewModel.authenticateAdmin(login, pass)){
                                        navHostController.navigate(Screen.Users.route)
                                        viewModel.getAllUsers()
                                    }
                                }
                            )
                        },
                        usersScreenContent = {
                            UsersScreen(
                                uiState = uiState,
                                onItemClick = {
                                    navHostController.navigate(Screen.Details.route)
                                    viewModel.getUser(it.login)
                                },
                                onBackClick = {
                                    navHostController.navigate(Screen.Login.route)
                                    viewModel.unAuthenticateAdmin()
                                }
                            )
                        },
                        detailsScreenContent = {
                            DetailsScreen(
                                uiState = uiState,
                                onBackClick = {
                                    navHostController.navigate(Screen.Users.route)
                                    viewModel.getAllUsers()
                                },
                                onAddOwnerClick = { viewModel.openOwnerDialog(it) },
                                onRoleClick = { viewModel.openRoleDialog(it) },
                                onExplorerClick = { viewModel.changeUserRole(it, "Explorer") },
                                onDeviceClick = { viewModel.changeUserRole(it, "Device") },
                                onSubmitOwnerClick = { user, owner -> viewModel.addUserOwner(user, owner) },
                                onCancelOwnerClick = { viewModel.getUser(it.login) }
                            )
                        }
                    )
                }
            }
        }
    }
}



