package ru.eshtykin.acs_monitoring_admin.presentation.screen.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ElectricMeter
import androidx.compose.material.icons.outlined.Monitor
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Green1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.LightGrey1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Yellow220

@Composable
fun UsersSection(
    users: List<User>?,
    onItemClick: (User) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),

        ) {
        LazyColumn() {
            users?.let {
                items(it) {
                    UserSmallCard(
                        user = it,
                        onItemClick = onItemClick
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun UserSmallCard(
    user: User,
    onItemClick: (User) -> Unit
) {
    val color = when (user.role) {
        "Explorer" -> Green1
        "Device" -> Yellow220
        else -> LightGrey1
    }
    val icon = when (user.role) {
        "Explorer" -> Icons.Outlined.Monitor
        "Device" -> Icons.Outlined.ElectricMeter
        else -> Icons.Outlined.QuestionMark
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onItemClick.invoke(user) },
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = icon,
                    contentDescription = null
                )
                Text(
                    text = user.role ?: "Unknown",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = user.login,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Owners: ")
                    Text(text = (user.owners?.size ?: 0).toString())
                }
            }
        }
    }
}