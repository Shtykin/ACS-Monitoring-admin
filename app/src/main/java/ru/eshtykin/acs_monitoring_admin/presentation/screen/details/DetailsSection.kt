package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ElectricMeter
import androidx.compose.material.icons.outlined.Monitor
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.eshtykin.acs_monitoring_admin.domain.entity.User
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Green1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.LightGrey1
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Yellow220

@Composable
fun DetailsSection(
    user: User,
    onRoleClick: (User) -> Unit,
    onAddOwnerClick: (User) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        DetailsSmallCard(
            user = user,
            onRoleClick = onRoleClick,
            onAddOwnerClick = onAddOwnerClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsSmallCard(
    user: User,
    onRoleClick: (User) -> Unit,
    onAddOwnerClick: (User) -> Unit
) {
    val txt = rememberSaveable() {
        mutableStateOf("123")
    }
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddOwnerClick.invoke(user) }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }
    ){paddingValues ->
        Column {
            Text(text = txt.value, color = Color.White)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = color),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(40.dp),
                        imageVector = icon,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = user.login,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.clickable { onRoleClick.invoke(user) },
                        text = user.role ?: "Empty",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Owners:",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.weight(1f)
            ){
                user.owners?.let {
                    items(it) {
                        Text(
                            text = it.value,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}