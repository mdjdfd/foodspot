package com.abmdigital.foodspotlight.ui.main.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelperImpl
import com.abmdigital.foodspotlight.data.model.User
import com.abmdigital.foodspotlight.utils.Resource
import kotlinx.coroutines.launch

class FeedViewModel @ViewModelInject constructor(private val dataRepository: MockApiHelperImpl) :
    ViewModel() {

    private val TAG: String = FeedViewModel::class.java.name

    private val _users = MutableLiveData<Resource<List<User>>>()

    val user: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))

            dataRepository.getUsers().let {
                if (it.isNotEmpty()) {
                    _users.postValue(Resource.success(it))
                } else {
                    _users.postValue(Resource.error(it.toString(), it))
                }
            }
        }
    }
}