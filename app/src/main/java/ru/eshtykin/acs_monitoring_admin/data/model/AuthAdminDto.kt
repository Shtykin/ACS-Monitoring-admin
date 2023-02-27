package ru.eshtykin.acs_monitoring_admin.data.model

import retrofit2.http.Field

data class AuthAdminDto(
    @Field("login") val login: String,
    @Field("password") val password: String
)
