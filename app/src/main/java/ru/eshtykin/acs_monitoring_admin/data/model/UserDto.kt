package ru.eshtykin.acs_monitoring_admin.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName ("login") val login: String,
    @SerializedName ("email") val email: String,
    @SerializedName ("role") val role: String,
    @SerializedName ("owner") val owner: List<String>,
)
