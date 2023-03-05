package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ru.eshtykin.acs_monitoring_admin.domain.entity.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.role.RoleDialogState
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.DarkGrey1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Green1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Yellow220

@Composable
fun RoleDialog(
    uiState: ScreenState,
    onExplorerClick: (User) -> Unit,
    onDeviceClick: (User) -> Unit
) {
    (uiState as? ScreenState.RoleDialog)?.let {
        val user = (uiState.state as RoleDialogState.Roles).user

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
}