package ru.eshtykin.acs_monitoring_admin.data.repository

import android.util.Log
import retrofit2.Response
import ru.eshtykin.acs_monitoring_admin.data.mapper.Mapper
import ru.eshtykin.acs_monitoring_admin.data.model.*
import ru.eshtykin.acs_monitoring_admin.data.network.ApiFactory
import ru.eshtykin.acs_monitoring_admin.domain.Owner
import ru.eshtykin.acs_monitoring_admin.domain.Repository
import ru.eshtykin.acs_monitoring_admin.domain.User

class RepositoryImpl : Repository {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()

    override suspend fun checkServer(): String =
        apiService.checkServer()

    override suspend fun authAdmin(login: String, password: String): String {
        val response = apiService.authAdmin(AuthAdminDto(login, password))
        response.body()?.let { return it.token }
        throw IllegalStateException(response.errorBody()?.string())
    }

    override suspend fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val response = apiService.getAllUsers()
        response.body()?.let{ it ->
            it.forEach { userDto ->
                users.add(mapper.mapUserDtoToUser(userDto))
            }
        }
        if (users.isNotEmpty()) return users
        else throw IllegalStateException(response.errorBody()?.string())
    }

    override suspend fun getUser(login: String): User {
            val response = apiService.getUser(LoginDto(login))
            response.body()?.let { return mapper.mapUserDtoToUser(it) }
            throw IllegalStateException(response.errorBody()?.string())
    }

    override suspend fun changeRole(login: String, role: String): Boolean {
        val response = apiService.setRole(ChangeRoleDto(login, role))
        response.body()?.let {
            return it.login == login && it.role == role
        }
        throw IllegalStateException(response.errorBody()?.string())
    }

    override suspend fun addOwner(login: String, owner: String): Owner {
        val response = apiService.addOwner(AddOwnerDto(login, owner))
        response.body()?.let { return Owner(it.owner) }
        throw IllegalStateException(response.errorBody()?.string())
    }
}