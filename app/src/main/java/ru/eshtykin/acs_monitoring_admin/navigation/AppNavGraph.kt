package ru.eshtykin.acs_monitoring_admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    startScreenRoute: String,
    navHostController: NavHostController,
    loginScreenContent: @Composable () -> Unit,
    usersScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable () -> Unit
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
    }
}