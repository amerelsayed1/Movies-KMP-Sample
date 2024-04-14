package com.iamer.moviessample.kmp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieRemote(
    val id: Int,
    val title: String?=null,
    val overview: String?=null,
    @SerialName("poster_path")
    val posterImage: String?=null,
    @SerialName("release_date")
    val releaseDate: String?=null,
)
