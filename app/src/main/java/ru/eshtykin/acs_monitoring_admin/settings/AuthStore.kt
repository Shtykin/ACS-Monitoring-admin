package ru.eshtykin.acs_monitoring_admin.settings

interface AuthStore {
    var login: String
    var password: String
    var accessToken: String
    fun isAuthenticated(): Boolean
    fun clearCredentials(): Boolean
}