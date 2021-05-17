package id.nns.movie_application.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val isFavorite: Boolean
) : Parcelable