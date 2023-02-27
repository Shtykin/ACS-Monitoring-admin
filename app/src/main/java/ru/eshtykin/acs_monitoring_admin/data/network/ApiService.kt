package ru.eshtykin.acs_monitoring_admin.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getInformation( ): String
}