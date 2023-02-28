package ru.eshtykin.acs_monitoring_admin.navigation

sealed class Screen(
    val route: String
) {
    object Login: Screen(ROUTE_LOGIN)
    object Users: Screen(ROUTE_USERS)
    object Details: Screen(ROUTE_DETAILS)

    private companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_USERS = "users"
        const val ROUTE_DETAILS = "details"
    }
}