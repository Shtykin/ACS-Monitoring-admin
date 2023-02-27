package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.LoadingSection
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

@Composable
fun LoginScreen(
    uiState: LoginScreenState
) {
    val scope = rememberCoroutineScope()

    when (uiState) {
        is LoginScreenState.Loading ->{
            LoadingSection()
        }
        is LoginScreenState.Authorized -> {

        }
        is LoginScreenState.UnAuthorized -> {
            UnAuthorizedLoginSection(
                scope = scope,
                onLoginClick = { login, pass ->
                    Log.e("DEBUG", "$login, $pass")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Acs_monitoringadminTheme {
        LoginScreen(LoginScreenState.Loading)
    }
}