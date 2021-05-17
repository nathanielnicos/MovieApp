package id.nns.movie_application.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.nns.movie_application.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getFavoriteMovies().asLiveData()
}