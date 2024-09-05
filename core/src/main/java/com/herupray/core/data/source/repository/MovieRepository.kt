package com.herupray.core.data.source.repository


import android.provider.ContactsContract.Data
import com.herupray.core.data.source.NetworkBoundResource
import com.herupray.core.data.source.Resource
import com.herupray.core.data.source.local.LocalDataSource
import com.herupray.core.data.source.remote.RemoteDataSource
import com.herupray.core.data.source.remote.network.ApiResponse
import com.herupray.core.data.source.remote.response.GetMovieResponse
import com.herupray.core.domain.model.Movie
import com.herupray.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


interface MovieRepository {
    fun getMovie(): Flow<Resource<List<Movie>>>
    suspend fun insertMovieToDB(movie: Movie)
    fun getAllFavoriteMovieFromDB(): Flow<List<Movie>>
    suspend fun deleteMovieFromDB(movie: Movie)
    fun getFavoriteStateMovieFromDB(id: Int): Flow<Boolean>
}

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {


    override fun getMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, GetMovieResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<GetMovieResponse>> =
                remoteDataSource.getMoviePopular()

            override fun loadFromNetwork(data: GetMovieResponse): Flow<List<Movie>> =
                DataMapper.mapListResponseToDomain(data.results)
        }.asFlow()



    override suspend fun insertMovieToDB(movie: Movie) =
        localDataSource.insert(DataMapper.mapDomainToEntity(movie))

    override fun getAllFavoriteMovieFromDB(): Flow<List<Movie>> =
        localDataSource.getAllFavorite().map {
            DataMapper.mapListEntityToDomain(it)
        }

    override suspend fun deleteMovieFromDB(movie: Movie) =
        localDataSource.delete(DataMapper.mapDomainToEntity(movie))

    override fun getFavoriteStateMovieFromDB(id: Int): Flow<Boolean> =
        localDataSource.getFavoriteState(id)

}