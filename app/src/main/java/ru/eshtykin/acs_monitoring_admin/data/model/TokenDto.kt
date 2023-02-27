package ru.eshtykin.acs_monitoring_admin.data.model

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName ("token") val token: String
)
