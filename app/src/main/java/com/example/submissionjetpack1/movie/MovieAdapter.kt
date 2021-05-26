package com.example.submissionjetpack1.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionjetpack1.R
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.databinding.ItemsMovietvshowBinding
import com.example.submissionjetpack1.detail.DetailTvshowActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?){
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }


    class MovieViewHolder (private val binding: ItemsMovietvshowBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: MovieEntity){
                with(binding){
                    tvItemTitle.text = movie.title
                    tvItemRelease.text = movie.release
                    tvItemGenre.text = movie.genre
                    Glide.with(itemView.context)
                        .load(movie.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                        .into(imgPoster)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailTvshowActivity::class.java)
                        intent.putExtra(DetailTvshowActivity.EXTRA_TVSHOW, movie.id)
                        intent.putExtra(DetailTvshowActivity.EXTRA_TYPE, movie.type)
                        itemView.context.startActivity(intent)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovietvshowBinding = ItemsMovietvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovietvshowBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}