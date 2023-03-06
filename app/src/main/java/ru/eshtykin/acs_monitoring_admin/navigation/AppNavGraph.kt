package ru.eshtykin.acs_monitoring_admin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppNavGraph(
    startScreenRoute: String,
    navHostController: NavHostController,
    loginScreenContent: @Composable () -> Unit,
    usersScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable () -> Unit,
    roleDialogContent: @Composable () -> Unit,
    ownerDialogContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = startScreenRoute
    ) {
        composable(Screen.Login.route) {
            loginScreenContent()
        }
        composable(Screen.Users.route) {
            usersScreenContent()
        }
        composable(Screen.Details.route) {
            detailsScreenContent()
        }
        dialog(
            Screen.ChangeRoleDialog.route,
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ){
            roleDialogContent()
        }
        dialog(
            Screen.AddOwnerDialog.route,
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ){
            ownerDialogContent()
        }
    }
}