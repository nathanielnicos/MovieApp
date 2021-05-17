package id.nns.movie_application.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.nns.movie_application.core.domain.usecase.MovieInteractor
import id.nns.movie_application.core.domain.usecase.MovieUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor) : MovieUseCase

}