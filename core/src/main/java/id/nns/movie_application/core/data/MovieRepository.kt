package id.nns.movie_application.core.data

import id.nns.movie_application.core.data.source.local.LocalDataSource
import id.nns.movie_application.core.data.source.remote.RemoteDataSource
import id.nns.movie_application.core.data.source.remote.network.ApiResponse
import id.nns.movie_application.core.data.source.remote.response.MovieResponse
import id.nns.movie_application.core.domain.model.MovieModel
import id.nns.movie_application.core.domain.repository.IMovieRepository
import id.nns.movie_application.core.utils.AppExecutors
import id.nns.movie_application.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<MovieModel>>> =
        object : NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<MovieModel>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: MovieModel, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}

