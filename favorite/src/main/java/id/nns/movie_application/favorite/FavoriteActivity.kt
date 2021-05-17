package id.nns.movie_application.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.nns.movie_application.core.ui.MovieAdapter
import id.nns.movie_application.detail.DetailActivity
import id.nns.movie_application.di.FavoriteModuleDependencies
import id.nns.movie_application.favorite.databinding.ActivityFavoriteBinding
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.movie.observe(this, { dataMovie ->
            movieAdapter.setData(dataMovie)
            binding.viewEmpty.root.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}