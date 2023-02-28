package ru.eshtykin.acs_monitoring_admin.presentation.screen.users

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.LoadingSection
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState

@Composable
fun UsersScreen(
    uiState: ScreenState,
    onItemClick: (User) -> Unit,
    onBackClick: (() -> Unit)?,
) {
    val scope = rememberCoroutineScope()

    BackHandler {
        onBackClick?.invoke()
    }

    (uiState as? ScreenState.UsersScreen)?.let {
        when (it.state) {
            is UsersScreenState.Loading -> {
                LoadingSection()
            }
            is UsersScreenState.Error -> {
                ErrorUsersSection(message = it.state.message)

            }
            is UsersScreenState.Users -> {
                UsersSection(
                    users = it.state.users,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

