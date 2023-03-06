package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                    text = "Role Assignment",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "User ${user.login} have current role ${user.role}. Choose role."
                )
                Spacer(modifier = Modifier.height(8.dp))
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
            }
        }
    }
}

private val DialogPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 18.dp)

private val MinWidth = 280.dp
private val MaxWidth = 560.dp