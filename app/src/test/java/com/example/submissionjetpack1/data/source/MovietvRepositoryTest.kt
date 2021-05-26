package com.example.submissionjetpack1.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.submissionjetpack1.data.source.remote.RemoteDataSource
import com.example.submissionjetpack1.utils.DataDummy
import com.example.submissionjetpack1.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovietvRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movietvRepository = FakeMovietvRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvshowResponses = DataDummy.generateRemoteDummyTv()
    private val tvshowId = tvshowResponses[0].id

    @Test
    fun getAllMovies(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(movietvRepository.getAllMovie())
        verify(remote).getAllMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvshows(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvshowsCallback)
                .onAllTvshowsReceived(tvshowResponses)
            null
        }.`when`(remote).getAllTv(any())
        val tvshowEntities = LiveDataTestUtil.getValue(movietvRepository.getAllTv())
        verify(remote).getAllTv(any())
        assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses.size.toLong(), tvshowEntities.size.toLong())
    }

    @Test
    fun getMovie(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovie(any())

        val movieEntities = LiveDataTestUtil.getValue(movietvRepository.getMovie(movieId))
        verify(remote).getAllMovie(any())
        assertNotNull(movieEntities)
        assertNotNull(movieEntities.title)
        assertEquals(movieResponses[0].title, movieEntities.title)
    }

    @Test
    fun getTvshow(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvshowsCallback)
                .onAllTvshowsReceived(tvshowResponses)
            null
        }.`when`(remote).getAllTv(any())

        val tvshowEntities = LiveDataTestUtil.getValue(movietvRepository.getTvshow(tvshowId))
        verify(remote).getAllTv(any())
        assertNotNull(tvshowEntities)
        assertNotNull(tvshowEntities.title)
        assertEquals(tvshowResponses[0].title, tvshowEntities.title)
    }

}