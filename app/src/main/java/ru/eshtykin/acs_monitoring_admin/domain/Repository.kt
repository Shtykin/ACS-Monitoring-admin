package ru.eshtykin.acs_monitoring_admin.domain

interface Repository {
    suspend fun getServerInfo(): String
}