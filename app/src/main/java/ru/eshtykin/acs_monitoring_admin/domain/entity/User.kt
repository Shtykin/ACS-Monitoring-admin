package ru.eshtykin.acs_monitoring_admin.domain.entity

import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner

data class User(
    val login: String,
    val role: String? = null,
    val owners: List<Owner>? = null
)
