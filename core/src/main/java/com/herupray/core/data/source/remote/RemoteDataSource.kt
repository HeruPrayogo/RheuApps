package com.herupray.core.data.source.remote


import com.herupray.core.data.source.remote.network.ApiResponse
import com.herupray.core.data.source.remote.network.ApiService
import com.herupray.core.data.source.remote.response.GetMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMoviePopular(): Flow<ApiResponse<GetMovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovie()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}