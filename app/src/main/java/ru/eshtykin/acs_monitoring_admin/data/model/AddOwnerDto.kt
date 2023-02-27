package ru.eshtykin.acs_monitoring_admin.data.model

import com.google.gson.annotations.SerializedName

data class AddOwnerDto(
    @SerializedName ("login") val login: String,
    @SerializedName ("owner") val owner: String,
)
