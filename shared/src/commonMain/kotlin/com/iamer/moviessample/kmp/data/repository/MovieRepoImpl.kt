package com.iamer.moviessample.kmp.data.repository

import com.iamer.moviessample.kmp.data.remote.RemoteDataSource
import com.iamer.moviessample.kmp.data.util.toMovie
import com.iamer.moviessample.kmp.domain.model.Movie
import com.iamer.moviessample.kmp.domain.repository.MovieRepo

internal class MovieRepoImpl(private val remoteDataSource: RemoteDataSource) : MovieRepo {

    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDataSource.getSingMovie(movieId).toMovie()
    }

}