package ru.eshtykin.acs_monitoring_admin.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.eshtykin.acs_monitoring_admin.settings.AuthStore

class AuthInterceptor(
    private val authStore: AuthStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {
            val request = chain.request().appendToken()
            val response = chain.proceed(request)
            return try {
                if (response.code == 401) {
                    runBlocking {
                        //TODO получение нового токена
                    }
                    response.close()
                    chain.proceed(request.appendToken())
                } else {
                    response
                }
            } catch (e: Throwable) {
                response
            }
        }

    }

    private fun Request.appendToken(): Request {
        val authHeaderName = "Baerer-Autorization"
        return newBuilder()
            .removeHeader(authHeaderName)
            .addHeader(authHeaderName, authStore.accessToken)
            .build()
    }

}