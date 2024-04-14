package com.iamer.moviessample.kmp.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.logging.Logger

internal class MovieService : KtorApi() {

    suspend fun getMovies(page: Int = 1): MoviesResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getSingMovies(movieId: Int = 1): MovieRemote = client.get {
        pathUrl("movie/$movieId")
    }.body()

}