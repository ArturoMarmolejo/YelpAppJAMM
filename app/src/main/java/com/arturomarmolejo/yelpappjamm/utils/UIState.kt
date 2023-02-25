package com.arturomarmolejo.yelpappjamm.utils

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.items.Businesses

sealed class UIState<out T> {
    object LOADING: UIState<Nothing>()
    data class SUCCESS<T> (
        val response: T?,
        ) : UIState<T>()
    data class ERROR(val error: Exception): UIState<Nothing>()
}