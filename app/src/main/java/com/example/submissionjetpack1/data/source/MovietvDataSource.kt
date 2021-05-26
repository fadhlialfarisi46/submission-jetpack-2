package com.example.submissionjetpack1.data.source

import androidx.lifecycle.LiveData
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.data.TvShowEntity

interface MovietvDataSource {

    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getAllTv(): LiveData<List<TvShowEntity>>

    fun getTvshow(tvshowId: String): LiveData<TvShowEntity>

    fun getMovie(movieId: String): LiveData<MovieEntity>
}