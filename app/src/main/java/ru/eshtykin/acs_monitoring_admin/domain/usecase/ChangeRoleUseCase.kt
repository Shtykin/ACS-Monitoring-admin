package ru.eshtykin.acs_monitoring_admin.domain.usecase

import ru.eshtykin.acs_monitoring_admin.domain.Repository
import ru.eshtykin.acs_monitoring_admin.domain.entity.User

class ChangeRoleUseCase(private val repository: Repository) {
    suspend fun execute(user: User, role: String) = repository.changeRole(user.login, role)
}