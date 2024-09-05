package com.herupray.core.data.source.remote.network

import com.herupray.core.data.source.remote.response.GetMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("popular?api_key=$api_key")
    suspend fun getMovie(): GetMovieResponse

    companion object {
        private const val api_key = "e73ba4baa44323fa06e5497760f26ab5"
    }
}