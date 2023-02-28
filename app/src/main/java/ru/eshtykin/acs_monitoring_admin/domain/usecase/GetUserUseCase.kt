package ru.eshtykin.acs_monitoring_admin.domain.usecase

import ru.eshtykin.acs_monitoring_admin.domain.Repository

class GetUserUseCase(private val repository: Repository) {
    suspend fun execute(login: String) = repository.getUser(login)
}