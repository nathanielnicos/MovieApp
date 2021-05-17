package id.nns.movie_application.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.nns.movie_application.core.BuildConfig
import id.nns.movie_application.core.R
import id.nns.movie_application.core.domain.model.MovieModel
import id.nns.movie_application.core.databinding.ItemGridBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieModel>()
    var onItemClick: ((MovieModel) -> Unit)? = null

    fun setData(newListData: List<MovieModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGridBinding.bind(itemView)
        fun bind(data: MovieModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${BuildConfig.TMDB_IMG_URL}${data.poster_path}")
                    .into(ivPoster)
                tvTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}