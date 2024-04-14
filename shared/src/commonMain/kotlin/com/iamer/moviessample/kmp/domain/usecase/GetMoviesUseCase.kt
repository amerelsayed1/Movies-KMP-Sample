package com.iamer.moviessample.kmp.domain.usecase

import com.iamer.moviessample.kmp.domain.model.Movie
import com.iamer.moviessample.kmp.domain.repository.MovieRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {

    private val repo: MovieRepo by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie> {
        return repo.getMovies(page = page)
    }

}