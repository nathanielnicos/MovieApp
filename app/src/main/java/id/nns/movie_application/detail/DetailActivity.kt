package id.nns.movie_application.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.nns.movie_application.BuildConfig
import id.nns.movie_application.R
import id.nns.movie_application.core.domain.model.MovieModel
import id.nns.movie_application.databinding.ActivityDetailBinding

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<MovieModel>(EXTRA_DATA)
        showDetailTourism(detailMovie)
    }

    private fun showDetailTourism(movieModel: MovieModel?) {
        movieModel?.let {
            binding.tvTitleDetail.text = movieModel.title
            binding.tvDateDetail.text = movieModel.release_date
            binding.tvOverview.text = movieModel.overview
            Glide.with(this@DetailActivity)
                .load("${BuildConfig.TMDB_IMG_URL}${movieModel.poster_path}")
                .into(binding.ivPosterDetail)

            var statusFavorite = movieModel.isFavorite
            setStatusFavorite(statusFavorite)

            binding.fabDetail.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movieModel, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }

        binding.progressBar.visibility = View.GONE
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabDetail.setImageResource(R.drawable.ic_bookmark_remove)
        } else {
            binding.fabDetail.setImageResource(R.drawable.ic_bookmark_add)
        }
    }

}