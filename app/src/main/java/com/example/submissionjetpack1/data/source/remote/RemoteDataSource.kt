package com.example.submissionjetpack1.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.submissionjetpack1.data.source.remote.response.MovieResponse
import com.example.submissionjetpack1.data.source.remote.response.TvshowResponse
import com.example.submissionjetpack1.utils.EspressoIdlingResource
import com.example.submissionjetpack1.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY: Long = 1000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovie(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovie())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    fun getAllTv(callback: LoadTvshowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvshowsReceived(jsonHelper.loadTv())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY)
    }

    interface LoadTvshowsCallback {
        fun onAllTvshowsReceived(tvshowResponse: List<TvshowResponse>)
    }


}