package id.nns.movie_application.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.nns.movie_application.core.domain.usecase.MovieUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun movieUseCase() : MovieUseCase
}