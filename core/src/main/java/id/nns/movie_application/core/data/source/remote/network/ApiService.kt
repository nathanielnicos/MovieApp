package id.nns.movie_application.core.data.source.remote.network

import id.nns.movie_application.core.BuildConfig
import id.nns.movie_application.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/upcoming?api_key=${BuildConfig.API_KEY}")
    suspend fun getUpcomingMovies() : MovieResponse
}