package ru.eshtykin.acs_monitoring_admin.di

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eshtykin.acs_monitoring_admin.data.mapper.Mapper
import ru.eshtykin.acs_monitoring_admin.data.network.ApiService
import ru.eshtykin.acs_monitoring_admin.data.network.AuthInterceptor
import ru.eshtykin.acs_monitoring_admin.data.repository.RepositoryImpl
import ru.eshtykin.acs_monitoring_admin.domain.Repository
import ru.eshtykin.acs_monitoring_admin.network.Constants
import ru.eshtykin.acs_monitoring_admin.settings.AuthStore
import ru.eshtykin.acs_monitoring_admin.settings.AuthStoreImpl
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, mapper: Mapper): Repository {
        return RepositoryImpl(apiService, mapper)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(authStore: AuthStore): AuthInterceptor {
        return AuthInterceptor(authStore)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideMapper(): Mapper {
        return Mapper()
    }

    @Provides
    @Singleton
    fun provideAuthStore(sharedPreferences: SharedPreferences): AuthStore {
        return AuthStoreImpl(sharedPreferences)
    }

}