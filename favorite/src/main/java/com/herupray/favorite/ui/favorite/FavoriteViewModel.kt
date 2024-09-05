package com.herupray.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.herupray.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {
    val getFavorite = movieUseCase.getAllFavorite()
        .flowOn(Dispatchers.IO)
        .asLiveData()
}