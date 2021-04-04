package com.abmdigital.foodspotlight.ui.main.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelperImpl
import com.abmdigital.foodspotlight.repository.DataRepository

class SearchViewModel @ViewModelInject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    private val TAG: String = SearchViewModel::class.java.name

    init {
    }


}