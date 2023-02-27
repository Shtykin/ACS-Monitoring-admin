package ru.eshtykin.acs_monitoring_admin.domain

data class User(
    val login: String,
    val role: String? = null,
    val owners: List<Owner>? = null
)
