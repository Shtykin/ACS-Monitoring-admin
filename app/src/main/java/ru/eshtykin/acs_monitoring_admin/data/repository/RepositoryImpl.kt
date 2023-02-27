package ru.eshtykin.acs_monitoring_admin.data.repository

import retrofit2.Response
import ru.eshtykin.acs_monitoring_admin.data.model.*
import ru.eshtykin.acs_monitoring_admin.data.network.ApiFactory
import ru.eshtykin.acs_monitoring_admin.domain.Repository

class RepositoryImpl: Repository {
    private val apiService = ApiFactory.apiService
    override suspend fun checkServer(): String =
        apiService.checkServer()

    override suspend fun authAdmin(login: String, password: String): Response<TokenDto> =
        apiService.authAdmin(AuthAdminDto(login, password))

    override suspend fun getAllUsers(): Response<List<UserDto>> =
        apiService.getAllUsers()

    override suspend fun getUser(login: String): Response<UserDto> =
        apiService.getUser(LoginDto(login))

    override suspend fun changeRole(login: String, role: String): Response<ChangeRoleDto> =
        apiService.setRole(ChangeRoleDto(login, role))

    override suspend fun addOwner(login: String, owner: String): Response<AddOwnerDto> =
        apiService.addOwner(AddOwnerDto(login, owner))
}