package com.opensource.foodspotlight.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opensource.foodspotlight.data.api.ApiHelper
import com.opensource.foodspotlight.data.model.Currency
import com.opensource.foodspotlight.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val apiHelper: ApiHelper) :
    ViewModel() {
    private val TAG: String = CurrencyViewModel::class.java.name

    private val _currency = MutableLiveData<Resource<List<Currency>>>()

    val currency: LiveData<Resource<List<Currency>>>
        get() = _currency

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            _currency.postValue(Resource.loading(null))

            apiHelper.let {
                try {
                    _currency.postValue(Resource.success(it.getCurrency()))
                } catch (e: Exception) {
                    _currency.postValue(Resource.error(e.toString(), null))
                }
            }

        }
    }
}