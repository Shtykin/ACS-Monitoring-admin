package ru.eshtykin.acs_monitoring_admin.presentation.screen.details

import ru.eshtykin.acs_monitoring_admin.domain.entity.User

sealed class DetailsScreenState {

    object Loading : DetailsScreenState()

    data class Error(
        val message: String? = null
    ) : DetailsScreenState()

    data class Details(
        val user: User
    ) : DetailsScreenState()

    data class RoleDialog(
        val user: User
    ) : DetailsScreenState()

    data class OwnerDialog(
        val user: User
    ) : DetailsScreenState()

}