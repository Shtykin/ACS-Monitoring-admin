package ru.eshtykin.acs_monitoring_admin.presentation.screen.owner

import ru.eshtykin.acs_monitoring_admin.domain.entity.User

sealed class OwnerDialogState {

    data class Error(
        val user: User,
        val message: String? = null
    ) : OwnerDialogState()

    data class Owners(
        val user: User
    ) : OwnerDialogState()

}