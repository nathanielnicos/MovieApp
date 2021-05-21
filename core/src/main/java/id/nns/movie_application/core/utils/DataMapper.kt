package id.nns.movie_application.core.utils

import id.nns.movie_application.core.data.source.local.entity.MovieEntity
import id.nns.movie_application.core.data.source.remote.response.MovieResponse
import id.nns.movie_application.core.domain.model.MovieModel

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<MovieModel> =
        input.map {
            MovieModel(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: MovieModel) : MovieEntity =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.release_date,
            isFavorite = input.isFavorite
        )

}