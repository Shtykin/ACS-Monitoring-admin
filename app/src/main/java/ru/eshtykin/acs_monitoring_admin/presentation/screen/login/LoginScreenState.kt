package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

sealed class LoginScreenState {
    object Loading: LoginScreenState()
    object Authorized: LoginScreenState()
    object UnAuthorized: LoginScreenState()
}