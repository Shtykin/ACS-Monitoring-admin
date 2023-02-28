package ru.eshtykin.acs_monitoring_admin.data.mapper

import ru.eshtykin.acs_monitoring_admin.data.model.UserDto
import ru.eshtykin.acs_monitoring_admin.domain.entity.Owner
import ru.eshtykin.acs_monitoring_admin.domain.entity.User

class Mapper {
    fun mapUserDtoToUser(userDto: UserDto): User {
        val owners = mutableListOf<Owner>()
        userDto.owner.forEach {
            owners.add(Owner(value = it))
        }
        return User(
            login = userDto.login,
            role = userDto.role,
            owners = owners
        )
    }
}