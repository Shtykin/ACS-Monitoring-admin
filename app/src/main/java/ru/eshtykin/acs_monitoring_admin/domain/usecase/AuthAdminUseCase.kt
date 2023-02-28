package ru.eshtykin.acs_monitoring_admin.domain.usecase

import ru.eshtykin.acs_monitoring_admin.domain.Repository

class AuthAdminUseCase(private val repository: Repository) {
    suspend fun execute(login: String, password: String) = repository.authAdmin(login, password)
}