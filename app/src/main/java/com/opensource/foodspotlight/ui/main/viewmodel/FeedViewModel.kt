package com.opensource.foodspotlight.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opensource.foodspotlight.data.mockapi.MockApiHelper
import com.opensource.foodspotlight.data.model.User
import com.opensource.foodspotlight.repository.DataRepository
import com.opensource.foodspotlight.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()

    val user: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))

            dataRepository.let {
                if (it != null) {
                    try {
                        _users.postValue(Resource.success(it.getUsers()))
                    } catch (e: Exception) {
                        _users.postValue(Resource.error(e.toString(), null))
                    }
                }
            }
        }
    }
}