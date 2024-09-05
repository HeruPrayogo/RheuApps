package com.herupray.core.domain.usecase

import com.herupray.core.data.source.Resource
import com.herupray.core.data.source.repository.MovieRepository
import com.herupray.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getMovie(): Flow<Resource<List<Movie>>> = movieRepository.getMovie()
    override suspend fun insert(movie: Movie) = movieRepository.insertMovieToDB(movie)
    override fun getAllFavorite(): Flow<List<Movie>> = movieRepository.getAllFavoriteMovieFromDB()
    override suspend fun delete(movie: Movie) = movieRepository.deleteMovieFromDB(movie)
    override fun getFavoriteState(id: Int): Flow<Boolean> = movieRepository.getFavoriteStateMovieFromDB(id)
}