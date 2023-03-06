package ru.eshtykin.acs_monitoring_admin.presentation.screen.role

import ru.eshtykin.acs_monitoring_admin.domain.entity.User

sealed class RoleDialogState {

    object Loading : RoleDialogState()

    data class Error(
        val message: String? = null
    ) : RoleDialogState()

    data class Roles(
        val user: User
    ) : RoleDialogState()

}