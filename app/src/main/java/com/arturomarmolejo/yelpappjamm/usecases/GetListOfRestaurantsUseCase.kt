package com.arturomarmolejo.yelpappjamm.usecases

import android.location.Location
import android.util.Log
import com.arturomarmolejo.yelpappjamm.database.LocalRepository
import com.arturomarmolejo.yelpappjamm.model.domain.*

import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.arturomarmolejo.yelpappjamm.service.YelpRepository
import com.arturomarmolejo.yelpappjamm.utils.NetworkState
import com.arturomarmolejo.yelpappjamm.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "GetListOfRestaurantsUse"
class GetListOfRestaurantsUseCase @Inject constructor(
    private val networkState: NetworkState,
    private val localRepository: LocalRepository,
    private val yelpRepository: YelpRepository
) {
    operator fun invoke(location: Location): Flow<UIState<List<ModelBusinesses>>> = flow {
        emit(UIState.LOADING)
        if(networkState.isInternetEnabled()) {
            try {
                val response = yelpRepository.retrieveBusinesses(location.latitude.toString(), location.longitude.toString())
                if(response.isSuccessful) {
                   response.body()?.let{
                       localRepository.insertBusinesses(it.businesses.mapToBusinessesDomain())
                       val businessHistoryId = localRepository.insertSearchedLocation(it.businesses.insertLocationHistory())
                       val locationHistoryDomain = localRepository.getSearchedLocationById(businessHistoryId.toString().toInt())
                       val locationHistory = locationHistoryDomain.mapToLocationHistory()
                       val temp: List<DomainBusinesses> = localRepository.getBusinessesById(locationHistory.businessIdList)
                       Log.d(TAG, "invoke: $temp")
                        emit(UIState.SUCCESS(temp.mapToBusinesses()))
                   } ?: throw Exception("null response body")
                } else throw Exception(response.errorBody().toString())
            } catch(e: Exception) {
                emit(UIState.ERROR(e))
            }
        } else {
            emit(UIState.ERROR(Exception("Internet is not enabled.")))
        }
    }
}

