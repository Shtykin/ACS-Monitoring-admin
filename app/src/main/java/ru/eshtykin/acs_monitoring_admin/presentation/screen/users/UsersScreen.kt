package ru.eshtykin.acs_monitoring_admin.presentation.screen.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import ru.eshtykin.acs_monitoring_admin.domain.Owner
import ru.eshtykin.acs_monitoring_admin.domain.User
import ru.eshtykin.acs_monitoring_admin.presentation.screen.details.LoadingSection
import ru.eshtykin.acs_monitoring_admin.presentation.ui.theme.Acs_monitoringadminTheme

@Composable
fun UsersScreen(
    uiState: UsersScreenState
) {
    val scope = rememberCoroutineScope()

    when (uiState) {
        is UsersScreenState.Loading -> {
            LoadingSection()
        }
        is UsersScreenState.Error -> {
            ErrorUsersSection(message = uiState.message)

        }
        is UsersScreenState.Users -> {
            UsersSection(users = uiState.users)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    Acs_monitoringadminTheme {
//        val users = mutableListOf<User>()
//        repeat(5) {
//            users.add(User("login $it", role = "Explorer"))
//        }
//        val owners = mutableListOf<Owner>()
//        repeat(10) {
//            owners.add(Owner("Owner $it"))
//        }
//        users.add(User("login 5", role = "Explorer", owners = owners))
//        users.add(User("login 6", role = "Device"))
//        UsersScreen(UsersScreenState.Users(users))
    }
}

