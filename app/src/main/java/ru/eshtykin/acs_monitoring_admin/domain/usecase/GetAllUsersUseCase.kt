package ru.eshtykin.acs_monitoring_admin.domain.usecase

import ru.eshtykin.acs_monitoring_admin.domain.Repository

class GetAllUsersUseCase(private val repository: Repository) {
    suspend fun execute() = repository.getAllUsers()
}