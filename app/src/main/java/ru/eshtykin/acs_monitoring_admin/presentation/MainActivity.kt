package ru.eshtykin.acs_monitoring_admin.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.eshtykin.acs_monitoring_admin.navigation.AppNavGraph
import ru.eshtykin.acs_monitoring_admin.navigation.Screen
import ru.eshtykin.acs_monitoring_admin.notification.PushService.Companion.ACTION_SHOW_MESSAGE
import ru.eshtykin.acs_monitoring_admin.notification.PushService.Companion.INTENT_FILTER
import ru.eshtykin.acs_monitoring_admin.notification.PushService.Companion.KEY_ACTION
import ru.eshtykin.acs_monitoring_admin.notification.PushService.Companion.KEY_MESSAGE
import ru.eshtykin.acs_monitoring_admin.notification.PushService.Companion.newIntentFilter
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.OwnerDialog
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.RoleDialog
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.users.UsersScreen
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    val pushReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras
                extras?.keySet()?.firstOrNull { it == KEY_ACTION }?.let { key ->
                    when (extras.getString(key)) {
                        ACTION_SHOW_MESSAGE -> {
                            extras.getString(KEY_MESSAGE)?.let {
                                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                            }
                        }
                        else -> Log.e("DEBUG", "unknow key")
                    }
                }
            }
        }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(pushReceiver, newIntentFilter(INTENT_FILTER))
        setContent {
            Acs_monitoringadminTheme {
                val navHostController = rememberNavController()
                val scope = rememberCoroutineScope()
                val uiState by viewModel.uiState
                val startScreenRoute =
                    if (viewModel.isAuthenticated()) Screen.Users.route else Screen.Login.route
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
                                    if (viewModel.authenticateAdmin(login, pass)) {
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
                                onAddOwnerClick = {
                                    navHostController.navigate(Screen.AddOwnerDialog.route) {
                                        popUpTo(Screen.Details.route) { inclusive = true }
                                    }
                                    viewModel.openOwnerDialog(it)
                                },
                                onRoleClick = {
                                    navHostController.navigate(Screen.ChangeRoleDialog.route) {
                                        popUpTo(Screen.Details.route) { inclusive = true }
                                    }
                                    viewModel.openRoleDialog(it)
                                },
                            )
                        },
                        roleDialogContent = {
                            RoleDialog(
                                uiState = uiState,
                                onExplorerClick = {
                                    navHostController.navigate(Screen.Details.route)
                                    viewModel.changeUserRole(it, "Explorer")
                                },
                                onDeviceClick = {
                                    navHostController.navigate(Screen.Details.route)
                                    viewModel.changeUserRole(it, "Device")
                                }
                            )
                        },
                        ownerDialogContent = {
                            OwnerDialog(
                                uiState = uiState,
                                onSubmitOwnerClick = { user, owner ->
                                    scope.launch {
                                        if (viewModel.addUserOwner(user, owner)) {
                                            navHostController.navigate(Screen.Details.route)
                                        }
                                    }
                                },
                                onCancelOwnerClick = {
                                    navHostController.navigate(Screen.Details.route)
                                    viewModel.getUser(it.login)
                                }
                            )
                        }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(pushReceiver)
        super.onDestroy()
    }
}



