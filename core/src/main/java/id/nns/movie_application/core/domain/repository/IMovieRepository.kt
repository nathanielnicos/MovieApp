package id.nns.movie_application.core.domain.repository

import id.nns.movie_application.core.data.Resource
import id.nns.movie_application.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies() : Flow<Resource<List<MovieModel>>>

    fun getFavoriteMovies() : Flow<List<MovieModel>>

    fun setFavoriteMovie(movie: MovieModel, state: Boolean)

}