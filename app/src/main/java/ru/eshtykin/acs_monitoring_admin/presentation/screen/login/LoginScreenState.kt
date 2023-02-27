package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.DetailsScreenState

sealed class LoginScreenState {
    object Loading: LoginScreenState()
    object Authorized: LoginScreenState()
    data class UnAuthorized(
        val message: String? = null
    ): LoginScreenState()
}