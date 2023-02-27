package ru.eshtykin.acs_monitoring_admin.data.repository

import ru.eshtykin.acs_monitoring_admin.data.network.ApiFactory
import ru.eshtykin.acs_monitoring_admin.domain.Repository

class RepositoryImpl: Repository {
    private val apiService = ApiFactory.apiService
    override suspend fun getServerInfo(): String =
        apiService.getInformation()

}