package ru.eshtykin.acs_monitoring_admin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ru.eshtykin.acs_monitoring_admin.network.Constants
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreen
import ru.eshtykin.acs_monitoring_admin.presentation.screen.login.LoginScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Acs_monitoringadminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(LoginScreenState.Loading)
                }
            }
        }
    }
}



