package id.nns.movie_application.core.domain.usecase

import id.nns.movie_application.core.domain.model.MovieModel
import id.nns.movie_application.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: MovieModel, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}