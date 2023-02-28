package ru.eshtykin.acs_monitoring_admin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.eshtykin.acs_monitoring_admin.domain.Repository
import ru.eshtykin.acs_monitoring_admin.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideAddOwnerUseCase(repository: Repository): AddOwnerUseCase =
        AddOwnerUseCase(repository)

    @Provides
    fun provideAuthAdminUseCase(repository: Repository): AuthAdminUseCase =
        AuthAdminUseCase(repository)

    @Provides
    fun provideChangeRoleUseCase(repository: Repository): ChangeRoleUseCase =
        ChangeRoleUseCase(repository)

    @Provides
    fun provideGetAllUsersUseCase(repository: Repository): GetAllUsersUseCase =
        GetAllUsersUseCase(repository)

    @Provides
    fun provideGetUserUseCase(repository: Repository): GetUserUseCase =
        GetUserUseCase(repository)
}