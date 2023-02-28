package ru.eshtykin.acs_monitoring_admin.domain.usecase

import ru.eshtykin.acs_monitoring_admin.domain.Repository
import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner
import ru.eshtykin.acs_monitoring_admin.domain.entity.User

class AddOwnerUseCase(private val repository: Repository) {
    suspend fun execute(user: User, owner: Owner) = repository.addOwner(user.login, owner.value)
}