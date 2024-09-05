package com.herupray.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.herupray.core.data.source.Resource
import com.herupray.core.domain.model.Movie

interface MovieUseCase {
    fun getMovie(): Flow<Resource<List<Movie>>>
    suspend fun insert(movie: Movie)
    fun getAllFavorite(): Flow<List<Movie>>
    suspend fun delete(movie: Movie)
    fun getFavoriteState(id: Int): Flow<Boolean>
}