package com.opensource.foodspotlight.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opensource.foodspotlight.data.model.User
import com.opensource.foodspotlight.repository.DataRepository
import com.opensource.foodspotlight.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    private val TAG: String = FeedViewModel::class.java.name

    private val _users = MutableLiveData<Resource<List<User>>>()

    val user: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
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