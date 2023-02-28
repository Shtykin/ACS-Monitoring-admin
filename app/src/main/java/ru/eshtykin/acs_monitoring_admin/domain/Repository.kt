package ru.eshtykin.acs_monitoring_admin.domain

import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner
import ru.eshtykin.acs_monitoring_admin.domain.entity.User

interface Repository {
    suspend fun checkServer(): String
    suspend fun authAdmin(login: String, password: String): String
    suspend fun getAllUsers(): List<User>
    suspend fun getUser(login: String): User
    suspend fun changeRole(login: String, role: String): Boolean
    suspend fun addOwner(login: String, owner: String): Owner
}