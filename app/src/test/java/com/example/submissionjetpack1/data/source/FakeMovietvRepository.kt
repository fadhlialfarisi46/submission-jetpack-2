package com.example.submissionjetpack1.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissionjetpack1.data.MovieEntity
import com.example.submissionjetpack1.data.TvShowEntity
import com.example.submissionjetpack1.data.source.remote.RemoteDataSource
import com.example.submissionjetpack1.data.source.remote.response.MovieResponse
import com.example.submissionjetpack1.data.source.remote.response.TvshowResponse

class FakeMovietvRepository (private val remoteDataSource: RemoteDataSource) :
    MovietvDataSource {


    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id,
                        response.type,
                        response.title,
                        response.description,
                        response.genre,
                        response.release,
                        response.runtime,
                        response.imagePath
                    )

                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTv(): LiveData<List<TvShowEntity>> {
        val tvshowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTv(object : RemoteDataSource.LoadTvshowsCallback {
            override fun onAllTvshowsReceived(tvshowResponse: List<TvshowResponse>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in tvshowResponse) {
                    val tv = TvShowEntity(
                        response.id,
                        response.type,
                        response.title,
                        response.description,
                        response.genre,
                        response.release,
                        response.runtime,
                        response.imagePath
                    )

                    tvList.add(tv)
                }
                tvshowResults.postValue(tvList)
            }
        })
        return tvshowResults
    }


    override fun getTvshow(tvshowId: String): LiveData<TvShowEntity> {
        val tvshowResult = MutableLiveData<TvShowEntity>()

        remoteDataSource.getAllTv(object : RemoteDataSource.LoadTvshowsCallback {
            override fun onAllTvshowsReceived(tvshowResponse: List<TvshowResponse>) {
                lateinit var tvshow: TvShowEntity
                for (response in tvshowResponse) {
                    if (response.id == tvshowId) {
                        tvshow = TvShowEntity(
                            response.id,
                            response.type,
                            response.title,
                            response.description,
                            response.genre,
                            response.release,
                            response.runtime,
                            response.imagePath
                        )
                    }
                }
                tvshowResult.postValue(tvshow)
            }
        })
        return tvshowResult
    }

    override fun getMovie(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()

        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.id == movieId) {
                        movie = MovieEntity(
                            response.id,
                            response.type,
                            response.title,
                            response.description,
                            response.genre,
                            response.release,
                            response.runtime,
                            response.imagePath
                        )
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }
}