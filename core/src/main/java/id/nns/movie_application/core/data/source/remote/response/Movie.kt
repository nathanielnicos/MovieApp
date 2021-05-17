package id.nns.movie_application.core.data.source.remote.response

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)