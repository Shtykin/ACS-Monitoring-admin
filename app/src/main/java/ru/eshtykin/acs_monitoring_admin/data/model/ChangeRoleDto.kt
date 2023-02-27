package ru.eshtykin.acs_monitoring_admin.data.model

import com.google.gson.annotations.SerializedName

data class ChangeRoleDto(
    @SerializedName ("login") val login: String,
    @SerializedName ("role") val role: String,
)
