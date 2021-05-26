package com.example.submissionjetpack1.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.data.source.MovietvRepository


class MovieViewModel(private val movietvRepository: MovietvRepository): ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movietvRepository.getAllMovie()
}