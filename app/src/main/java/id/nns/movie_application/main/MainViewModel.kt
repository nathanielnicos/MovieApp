package id.nns.movie_application.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.nns.movie_application.core.domain.usecase.MovieUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies().asLiveData()
}