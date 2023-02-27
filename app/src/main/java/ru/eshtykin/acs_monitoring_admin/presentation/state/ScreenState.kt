package ru.eshtykin.acs_monitoring_admin.presentation.state

import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreenState

sealed class ScreenState{

    data class LoginScreen(
        val state: LoginScreenState
    ) : ScreenState()

    data class DetailsScreen(
        val state: DetailsScreenState
    ) : ScreenState()

    data class UsersScreen(
        val state: UsersScreen
    ) : ScreenState()
}
