package id.nns.movie_application.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.nns.movie_application.core.domain.model.MovieModel
import id.nns.movie_application.core.domain.usecase.MovieUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: MovieModel, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}