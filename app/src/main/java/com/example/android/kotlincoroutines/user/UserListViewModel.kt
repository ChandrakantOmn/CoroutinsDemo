package com.example.android.kotlincoroutines.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.kotlincoroutines.util.singleArgViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UserRepository) : ViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::UserListViewModel)
    }

    private val _snackBar = MutableLiveData<String>()

    val snackBar: LiveData<String>
        get() = _snackBar

    val title = repository.title

    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun onMainViewClicked() {
        getUsers()
    }

    fun onSnackbarShown() {
        _snackBar.value = null
    }

    private fun getUsers() {
        launchDataLoad {
            repository.getUsers(1, 100)
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: TitleRefreshError) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

}


