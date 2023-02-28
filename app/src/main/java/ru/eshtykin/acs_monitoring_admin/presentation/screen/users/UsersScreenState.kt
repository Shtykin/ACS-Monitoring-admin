package ru.eshtykin.acs_monitoring_admin.presentation.screen.users

import ru.eshtykin.acs_monitoring_admin.domain.entity.User

sealed class UsersScreenState {

    object Loading : UsersScreenState()

    data class Error(
        val message: String? = null
    ) : UsersScreenState()

    data class Users(
        val users: List<User>
    ) : UsersScreenState()

}