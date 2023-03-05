package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner
import ru.eshtykin.acs_monitoring_admin.domain.entity.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.owner.OwnerDialogState
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.DarkGrey1

@Composable
fun OwnerDialog(
    uiState: ScreenState,
    onSubmitOwnerClick: (User, Owner) -> Unit,
    onCancelOwnerClick: (User) -> Unit
) {
    (uiState as? ScreenState.OwnerDialog)?.let {

        val user = when (uiState.state){
            is OwnerDialogState.Owners -> uiState.state.user
            is OwnerDialogState.Error -> uiState.state.user
        }
        val error = (uiState.state as? OwnerDialogState.Error)?.message

        var owner by remember { mutableStateOf("") }
        val textFieldColors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        )

        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { },
            title = { Text(text = "Adding an Owner") },
            text = {
                Column() {
                    TextField(
                        value = owner,
                        onValueChange = { owner = it },
                        label = { Text("Owner") },
                        colors = textFieldColors
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    error?.let {
                        Text(
                            text = error,
                            color = Color.Red
                        )
                    }
                }
            },
            buttons = {
                Row() {
                    OutlinedButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = { onSubmitOwnerClick.invoke(user, Owner(owner)) },
                        ) {
                        Text(
                            text = "Submit",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                    OutlinedButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = { onCancelOwnerClick.invoke(user) },
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            },
            contentColor = DarkGrey1
        )
    }
}