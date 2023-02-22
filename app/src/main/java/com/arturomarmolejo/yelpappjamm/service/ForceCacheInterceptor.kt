package com.arturomarmolejo.yelpappjamm.service

import com.arturomarmolejo.yelpappjamm.utils.NetworkState
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.channels.NetworkChannel
import javax.inject.Inject

class ForceCacheInterceptor @Inject constructor(
    private val networkState: NetworkState
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            if(!networkState.isInternetEnabled()) {
                cacheControl(CacheControl.FORCE_CACHE)
            }
        }.build()
            .also { return chain.proceed(it) }
    }
}