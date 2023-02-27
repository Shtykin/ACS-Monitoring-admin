package ru.eshtykin.acs_monitoring_admin.data.network

import retrofit2.Response
import retrofit2.http.*
import ru.eshtykin.acs_monitoring_admin.data.model.*


interface ApiService {


    @GET("/")
    suspend fun checkServer(): String

    @POST("/admin/login")
    suspend fun authAdmin(
        @Body authAdmin: AuthAdminDto
    ): Response<TokenDto>

    @POST("/admin/users")
    suspend fun getAllUsers(): Response<List<UserDto>>

    @POST("/admin/user")
    suspend fun getUser(
        @Body login: LoginDto
    ): Response<UserDto>

    @POST("/admin/role")
    suspend fun setRole(
        @Body changeRoleDto: ChangeRoleDto
    ): Response<ChangeRoleDto>

    @POST("/admin/owner")
    suspend fun addOwner(
        @Body addOwnerDto: AddOwnerDto
    ): Response<AddOwnerDto>


}