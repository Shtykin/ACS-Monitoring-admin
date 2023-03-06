package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

        val user = when (uiState.state) {
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

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .wrapContentSize()
                .animateContentSize()
                .sizeIn(minWidth = MinWidth, maxWidth = MaxWidth),
            shape = RoundedCornerShape(28.0.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .sizeIn(minWidth = MinWidth, maxWidth = MaxWidth)
                    .padding(DialogPadding)
            ) {
                Text(
                    text = "Adding an Owner",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = owner,
                    onValueChange = { owner = it },
                    label = { Text("Owner") },
                    colors = textFieldColors
                )
                error?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = error,
                        color = Color.Red
                    )
                }
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
            }
        }
    }
}

private val DialogPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 18.dp)

private val MinWidth = 280.dp
private val MaxWidth = 560.dp