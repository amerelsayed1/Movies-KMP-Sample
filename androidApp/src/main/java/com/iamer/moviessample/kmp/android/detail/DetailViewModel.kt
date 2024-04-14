package com.iamer.moviessample.kmp.android.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamer.moviessample.kmp.android.home.HomeScreenState
import com.iamer.moviessample.kmp.domain.model.Movie
import com.iamer.moviessample.kmp.domain.usecase.GetMoviesUseCase
import com.iamer.moviessample.kmp.domain.usecase.GetSingleMovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetSingleMovieUseCase,
    val movieId: Int
) : ViewModel() {
     var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) {
        if (uiState.loading) return

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val movie = getMovieUseCase.invoke(movieId = movieId)
                uiState.copy(loading = false, movies = movie)
            } catch (error: Throwable) {
                uiState.copy(loading = false, errorMessage = error.localizedMessage)
            }
        }
    }

}


data class DetailScreenState(
    var loading: Boolean = false,
    var movies: Movie? = null,
    var errorMessage: String? = null,
)