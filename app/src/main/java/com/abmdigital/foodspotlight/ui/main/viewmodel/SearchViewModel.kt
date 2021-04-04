package com.abmdigital.foodspotlight.ui.main.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelperImpl

class SearchViewModel @ViewModelInject constructor(private val dataRepository: MockApiHelperImpl) :
    ViewModel() {
    private val TAG: String = SearchViewModel::class.java.name

    init {
    }


//    private val _text = MutableLiveData<String>().apply {
//        value = randomString
//    }
//    val text: LiveData<String> = _text
}