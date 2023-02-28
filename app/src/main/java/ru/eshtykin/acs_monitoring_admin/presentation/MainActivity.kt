package ru.eshtykin.acs_monitoring_admin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.eshtykin.acs_monitoring_admin.navigation.AppNavGraph
import ru.eshtykin.acs_monitoring_admin.navigation.Screen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreen
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Acs_monitoringadminTheme {
                val navHostController = rememberNavController()
                val uiState by viewModel.uiState
                val startScreenRoute = if (viewModel.isAuthenticated()) Screen.Users.route else Screen.Login.route
                if (viewModel.isAuthenticated()) viewModel.getAllUsers()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavGraph(
                        startScreenRoute = startScreenRoute,
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



