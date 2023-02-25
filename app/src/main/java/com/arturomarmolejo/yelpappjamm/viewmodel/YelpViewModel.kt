package com.arturomarmolejo.yelpappjamm.viewmodel

import android.location.Location
import android.provider.Contacts.Intents.UI
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.domain.ModelBusinesses
import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.arturomarmolejo.yelpappjamm.usecases.GetListOfRestaurantsUseCase
import com.arturomarmolejo.yelpappjamm.utils.NetworkState
import com.arturomarmolejo.yelpappjamm.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "YelpViewModel"
@HiltViewModel
class YelpViewModel @Inject constructor(
    private val getListOfRestaurantsUseCase: GetListOfRestaurantsUseCase,
    private val networkState: NetworkState
): ViewModel() {
    fun getNetworkState(): Boolean {
        return networkState.isInternetEnabled()
    }

    var location: Location? = null
    val arePermsGranted: Boolean = false
    var selectedBusinesses: ModelBusinesses?= null

    private val _businesses : MutableLiveData<UIState<List<ModelBusinesses>>> = MutableLiveData(UIState.LOADING)
    val businesses : MutableLiveData<UIState<List<ModelBusinesses>>> get() = _businesses

    fun getRestaurantsList() {
        viewModelScope.launch { 
            location?.let { location ->
                getListOfRestaurantsUseCase(location).collect() {
                    _businesses.postValue(it)
                }
            } ?: run { Log.d(TAG, "getRestaurantsList: No location provided")}
        }
    }
}