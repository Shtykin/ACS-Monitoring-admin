package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun UnAuthorizedLoginSection(
    scope: CoroutineScope,
    onLoginClick: suspend (String, String) -> Unit
) {
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = login,
                onValueChange = { login = it},
                label = { Text("Login") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = pass,
                onValueChange = { pass = it},
                label = { Text("Password") },
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        onLoginClick.invoke(login, pass)
                    }
                }
            ) {
                Text(
                    text = "SUBMIT",
                    color = Color.Black,
                    fontSize = 20.sp

                )
            }
        }
    }
}