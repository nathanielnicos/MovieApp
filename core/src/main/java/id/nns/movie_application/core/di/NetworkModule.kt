package id.nns.movie_application.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.nns.movie_application.core.BuildConfig
import id.nns.movie_application.core.data.source.remote.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun getApiService() : ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

}