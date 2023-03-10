package ru.eshtykin.acs_monitoring_admin.presentation.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
    message: String?,
    onLoginClick: suspend (String, String) -> Unit
) {
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val textFieldColors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = login,
                onValueChange = { login = it},
                label = { Text("Login") },
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = pass,
                onValueChange = { pass = it},
                label = { Text("Password") },
                colors = textFieldColors
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
                    text = "Submit",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            message?.let {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = message,
                    color = Color.Red
                )
            }

        }
    }
}