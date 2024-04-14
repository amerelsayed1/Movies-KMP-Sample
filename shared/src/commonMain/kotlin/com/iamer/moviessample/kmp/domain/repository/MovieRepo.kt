package com.iamer.moviessample.kmp.domain.repository

import com.iamer.moviessample.kmp.domain.model.Movie

internal interface MovieRepo {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId :Int): Movie
}