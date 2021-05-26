package com.example.submissionjetpack1.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submissionjetpack1.R
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.data.TvShowEntity
import com.example.submissionjetpack1.databinding.ActivityDetailMovieBinding
import com.example.submissionjetpack1.databinding.ContentDetailMovieBinding
import com.example.submissionjetpack1.viewmodel.ViewModelFactory

class DetailTvshowActivity : AppCompatActivity() {

    private lateinit var detailContentDetailMovieBinding: ContentDetailMovieBinding

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentDetailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)
        setSupportActionBar(activityDetailMovieBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvshowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvshowId = extras.getString(EXTRA_TVSHOW)
            when (extras.getString(EXTRA_TYPE)) {
                "tv" -> if (tvshowId != null) {
                    activityDetailMovieBinding.progressBar.visibility = View.VISIBLE

                    viewModel.setSelectedTvshow(tvshowId)
                    viewModel.getTvshow().observe(this, {tvshow ->
                        activityDetailMovieBinding.progressBar.visibility = View.GONE
                        populateTvshow(tvshow)
                    })
                }
                "movie" -> if (tvshowId != null) {
                    activityDetailMovieBinding.progressBar.visibility = View.VISIBLE

                    viewModel.setSelectedMovie(tvshowId)
                    viewModel.getMovie().observe(this, {movie ->
                        activityDetailMovieBinding.progressBar.visibility = View.GONE
                        populateMovie(movie)
                    })

                }
            }

        }
    }

    private fun populateTvshow(tvshowEntity: TvShowEntity) {
        with(detailContentDetailMovieBinding){
            textTitle.text = tvshowEntity.title
            textDescription.text = tvshowEntity.description
            textGenre.text = tvshowEntity.genre
            textRelease.text = tvshowEntity.release
            textRuntime.text = tvshowEntity.runtime

        }

        Glide.with(this)
            .load(tvshowEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentDetailMovieBinding.image)

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        with(detailContentDetailMovieBinding){
            textTitle.text = movieEntity.title
            textDescription.text = movieEntity.description
            textGenre.text = movieEntity.genre
            textRelease.text = movieEntity.release
            textRuntime.text = movieEntity.runtime

        }

        Glide.with(this)
            .load(movieEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentDetailMovieBinding.image)

    }
}