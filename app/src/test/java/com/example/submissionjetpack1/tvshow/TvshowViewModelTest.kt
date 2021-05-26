package com.example.submissionjetpack1.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submissionjetpack1.data.TvShowEntity
import com.example.submissionjetpack1.data.source.MovietvRepository
import com.example.submissionjetpack1.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest{

    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movietvRepository: MovietvRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvshowViewModel(movietvRepository)
    }

    @Test
    fun getTvshows(){
        val dummyTvshows = DataDummy.generateDummyTv()
        val tvshows = MutableLiveData<List<TvShowEntity>>()
        tvshows.value = dummyTvshows

        `when`(movietvRepository.getAllTv()).thenReturn(tvshows)
        val tvShowEntities = viewModel.getTvshows().value
        verify(movietvRepository).getAllTv()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvshows().observeForever(observer)
        verify(observer).onChanged(dummyTvshows)
    }

}