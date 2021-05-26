package com.example.submissionjetpack1.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.data.TvShowEntity
import com.example.submissionjetpack1.data.source.MovietvRepository

class DetailTvshowViewModel(private val movietvRepository: MovietvRepository) : ViewModel() {

    private lateinit var tvshowId: String

    fun setSelectedTvshow(tvshowId: String) {
        this.tvshowId = tvshowId
    }

    fun getTvshow(): LiveData<TvShowEntity> = movietvRepository.getTvshow(tvshowId)

    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = movietvRepository.getMovie(movieId)

}