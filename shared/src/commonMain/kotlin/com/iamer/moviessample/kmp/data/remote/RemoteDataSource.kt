package com.iamer.moviessample.kmp.data.remote

import com.iamer.moviessample.kmp.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {

    suspend fun getMovies(page:Int)= withContext(dispatcher.io){
        apiService.getMovies(page)
    }


    suspend fun getSingMovie(movieId:Int)= withContext(dispatcher.io){
        apiService.getSingMovies(movieId)
    }
}