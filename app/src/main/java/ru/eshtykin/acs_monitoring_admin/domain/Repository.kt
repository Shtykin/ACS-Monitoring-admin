package ru.eshtykin.acs_monitoring_admin.domain

import retrofit2.Response
import ru.eshtykin.acs_monitoring_admin.data.model.AddOwnerDto
import ru.eshtykin.acs_monitoring_admin.data.model.ChangeRoleDto
import ru.eshtykin.acs_monitoring_admin.data.model.TokenDto
import ru.eshtykin.acs_monitoring_admin.data.model.UserDto

interface Repository {
    suspend fun checkServer(): String
    suspend fun authAdmin(login: String, password: String): Response<TokenDto>
    suspend fun getAllUsers(): Response<List<UserDto>>
    suspend fun getUser(login: String): Response<UserDto>
    suspend fun changeRole(login: String, role: String): Response<ChangeRoleDto>
    suspend fun addOwner(login: String, owner: String): Response<AddOwnerDto>
}