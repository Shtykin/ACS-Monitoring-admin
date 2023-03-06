package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import ru.eshtykin.acs_monitoring_admin.domain.entity.User
import ru.eshtykin.acs_monitoring_admin.presentation.state.ScreenState

@Composable
fun DetailsScreen(
    uiState: ScreenState,
    onBackClick: (() -> Unit)?,
    onAddOwnerClick: (User) -> Unit,
    onRoleClick: (User) -> Unit,
) {

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
        }
    }
}