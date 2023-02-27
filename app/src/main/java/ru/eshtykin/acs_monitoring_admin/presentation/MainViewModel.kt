package ru.eshtykin.acs_monitoring_admin.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.eshtykin.acs_monitoring_admin.data.repository.RepositoryImpl

class MainViewModel(application: Application): AndroidViewModel(application) {

    val repository = RepositoryImpl()
    init {
        viewModelScope.launch {
            Log.e("DEBUG", repository.checkServer())
//            val response = repository.authAdmin("admin", "adminpass")
//            val response = repository.getAllUsers()
            val response = repository.getUser("user1")
//            val response = repository.changeRole("user1", "Explorer")
//            val response = repository.addOwner("user1", "ЗРУ-01_№010123")
            Log.e("DEBUG", "$response")
        }

    }
}