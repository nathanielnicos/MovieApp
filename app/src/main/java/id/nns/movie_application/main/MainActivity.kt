package id.nns.movie_application.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.nns.movie_application.R
import id.nns.movie_application.core.data.Resource
import id.nns.movie_application.core.ui.MovieAdapter
import id.nns.movie_application.databinding.ActivityMainBinding
import id.nns.movie_application.detail.DetailActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAdapter = MovieAdapter()
        mainAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        mainViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        mainAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bookmark -> {
                val uri = Uri.parse("movie_application://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}