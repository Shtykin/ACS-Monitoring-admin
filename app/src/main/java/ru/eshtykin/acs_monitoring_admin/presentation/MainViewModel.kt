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
        viewModelScope.launch { Log.e("DEBUG", repository.getServerInfo()) }
    }
}