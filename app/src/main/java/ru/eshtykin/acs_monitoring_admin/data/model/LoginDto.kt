package ru.eshtykin.acs_monitoring_admin.data.model

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("login") val login: String
)
