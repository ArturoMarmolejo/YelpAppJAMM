package com.arturomarmolejo.yelpappjamm.utils

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses

sealed class UIState {
    object LOADING: UIState()
    data class SUCCESS (
        val response: DomainBusinesses,
        val isOffline: Boolean = false
        ) : UIState()
    data class ERROR(val error: java.lang.Exception): UIState()
}