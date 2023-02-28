package ru.eshtykin.acs_monitoring_admin.settings

import android.content.SharedPreferences

class AuthStoreImpl(private val sharedPreferences: SharedPreferences) : AuthStore {

    override var login: String
        get() = getStringPref(LOGIN_KEY)
        set(value) { putStringPref(LOGIN_KEY, value) }

    override var password: String
        get() = getStringPref(PASSWORD_KEY)
        set(value) { putStringPref(PASSWORD_KEY, value) }

    override var accessToken: String
        get() = getStringPref(TOKEN_KEY)
        set(value) { putStringPref(TOKEN_KEY, value) }

    override fun isAuthenticated(): Boolean {
        return !accessToken.isEmpty()
    }

    override fun clearCredentials(): Boolean = sharedPreferences
        .edit()
        .clear()
        .commit()

    private fun getStringPref(key: String): String =
        if (sharedPreferences.contains(key)) sharedPreferences.getString(key, "") ?: ""
        else ""

    private fun putStringPref(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    companion object {
        private const val LOGIN_KEY = "AuthStore.login"
        private const val PASSWORD_KEY = "AuthStore.password"
        private const val TOKEN_KEY = "AuthStore.accessToken"
    }
}