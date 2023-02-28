package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ru.eshtykin.acs_monitoring_admin.domain.Owner
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.DarkGrey1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Green1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Yellow220

@Composable
fun DetailsScreen(
    uiState: ScreenState,
    onBackClick: (() -> Unit)?,
    onAddOwnerClick: (User) -> Unit,
    onRoleClick: (User) -> Unit,
    onExplorerClick: (User) -> Unit,
    onDeviceClick: (User) -> Unit,
    onSubmitOwnerClick: (User, Owner) -> Unit,
    onCancelOwnerClick: (User) -> Unit
) {
    val scope = rememberCoroutineScope()
//    val openRoleDialog = remember { mutableStateOf(false) }

    BackHandler {
        onBackClick?.invoke()
    }

    (uiState as? ScreenState.DetailsScreen)?.let {
        when (it.state) {
            is DetailsScreenState.Loading -> {
                LoadingSection()
            }
            is DetailsScreenState.Error -> {
                ErrorDetailsSection(message = it.state.message)

            }
            is DetailsScreenState.Details -> {
                DetailsSection(
                    user = it.state.user,
                    onRoleClick = onRoleClick,
                    onAddOwnerClick = onAddOwnerClick
                )
            }
            is DetailsScreenState.RoleDialog -> {
                RoleDialog(
                    user = it.state.user,
                    onExplorerClick = onExplorerClick,
                    onDeviceClick = onDeviceClick
                )
            }
            is DetailsScreenState.OwnerDialog -> {
                OwnerDialog(
                    user = it.state.user,
                    onSubmitOwnerClick = onSubmitOwnerClick,
                    onCancelOwnerClick = onCancelOwnerClick
                )
            }
        }
    }
}

@Composable
fun OwnerDialog(
    user: User,
    onSubmitOwnerClick: (User, Owner) -> Unit,
    onCancelOwnerClick: (User) -> Unit
) {
    var owner by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Adding an Owner") },
        text = {
               Column() {
//                   Text("У пользователя ${user.login} текущая роль ${user.role}. Выбрать:")
                   TextField(
                       value = owner,
                       onValueChange = { owner = it},
                       label = { Text("Owner") },
                   )
               }
        },
        buttons = {
            Row() {
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onSubmitOwnerClick.invoke(user, Owner(owner)) },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Green1)
                ) {
                    Text("Submit", fontSize = 22.sp)
                }
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onCancelOwnerClick.invoke(user) },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow220)
                ) {
                    Text("Cancel", fontSize = 22.sp)
                }
            }

        },
        contentColor = DarkGrey1
    )
}

@Composable
fun RoleDialog(
    user: User,
    onExplorerClick: (User) -> Unit,
    onDeviceClick: (User) -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Role Assignment") },
        text = { Text("User ${user.login} have current role ${user.role}. Choose new:") },
        buttons = {
            Row() {
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onExplorerClick.invoke(user) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green1)
                ) {
                    Text("Explorer", fontSize = 22.sp)
                }
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onDeviceClick.invoke(user) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow220)
                ) {
                    Text("Device", fontSize = 22.sp)
                }
            }

        },
        contentColor = DarkGrey1
    )
}