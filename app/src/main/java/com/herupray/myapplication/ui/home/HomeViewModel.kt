package com.herupray.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.herupray.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    val getMovie = movieUseCase.getMovie().asLiveData()
}