package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.LoadingSection
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState

@Composable
fun LoginScreen(
    uiState: ScreenState,
    onLoginClick: suspend (String, String) -> Unit
) {
    val scope = rememberCoroutineScope()

    (uiState as? ScreenState.LoginScreen)?.let {
        when (it.state) {
            is LoginScreenState.Loading -> {
                LoadingSection()
            }
            is LoginScreenState.Authorized -> {

            }
            is LoginScreenState.UnAuthorized -> {
                UnAuthorizedLoginSection(
                    scope = scope,
                    message = it.state.message,
                    onLoginClick = onLoginClick
                )
            }
        }
    }
}