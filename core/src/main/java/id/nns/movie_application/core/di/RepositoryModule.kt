package id.nns.movie_application.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.nns.movie_application.core.data.MovieRepository
import id.nns.movie_application.core.domain.repository.IMovieRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository) : IMovieRepository

}