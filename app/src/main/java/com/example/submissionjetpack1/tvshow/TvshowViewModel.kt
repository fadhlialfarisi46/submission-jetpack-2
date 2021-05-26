package com.example.submissionjetpack1.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack1.data.TvShowEntity
import com.example.submissionjetpack1.data.source.MovietvRepository

class TvshowViewModel(private val movietvRepository: MovietvRepository): ViewModel() {

    fun getTvshows(): LiveData<List<TvShowEntity>> = movietvRepository.getAllTv()
}