package ru.eshtykin.acs_monitoring_admin.navigation

sealed class Screen(
    val route: String
) {
    object Login: Screen(ROUTE_LOGIN)
    object Users: Screen(ROUTE_USERS)
    object Details: Screen(ROUTE_DETAILS)
    object ChangeRoleDialog: Screen(ROUTE_CHANGE_ROLE_DIALOG)
    object AddOwnerDialog: Screen(ROUTE_ADD_OWNER_DIALOG)

    private companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_USERS = "users"
        const val ROUTE_DETAILS = "details"
        const val ROUTE_CHANGE_ROLE_DIALOG = "change_role_dialog"
        const val ROUTE_ADD_OWNER_DIALOG = "add_owner_dialog"
    }
}