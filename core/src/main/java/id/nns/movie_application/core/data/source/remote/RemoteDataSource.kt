package id.nns.movie_application.core.data.source.remote

import id.nns.movie_application.core.data.source.remote.network.ApiResponse
import id.nns.movie_application.core.data.source.remote.network.ApiService
import id.nns.movie_application.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies() : Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getUpcomingMovies()
                val results = response.results

                if (results.isNotEmpty()) {
                    emit(ApiResponse.Success(results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}