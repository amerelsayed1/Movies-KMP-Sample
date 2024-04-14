package com.iamer.moviessample.kmp.data.util

import com.iamer.moviessample.kmp.data.remote.MovieRemote
import com.iamer.moviessample.kmp.domain.model.Movie

internal fun MovieRemote.toMovie(): Movie {
    return Movie(
        id = id,
        title = title?:"",
        description = overview?:"",
        imageUrl = getImageUrl(posterImage?:""),
        releaseDate = releaseDate?:""
    )
}

private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"