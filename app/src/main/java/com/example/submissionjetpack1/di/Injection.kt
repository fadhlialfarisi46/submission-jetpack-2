package com.example.submissionjetpack1.di

import android.content.Context
import com.example.submissionjetpack1.data.source.MovietvRepository
import com.example.submissionjetpack1.data.source.remote.RemoteDataSource
import com.example.submissionjetpack1.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovietvRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MovietvRepository.getInstance(remoteDataSource)
    }
}