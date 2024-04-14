package com.iamer.moviessample.kmp.di

import com.iamer.moviessample.kmp.data.remote.MovieService
import com.iamer.moviessample.kmp.data.remote.RemoteDataSource
import com.iamer.moviessample.kmp.data.repository.MovieRepoImpl
import com.iamer.moviessample.kmp.domain.repository.MovieRepo
import com.iamer.moviessample.kmp.domain.usecase.GetMoviesUseCase
import com.iamer.moviessample.kmp.domain.usecase.GetSingleMovieUseCase
import com.iamer.moviessample.kmp.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory {
        RemoteDataSource(get(), get())
    }
    factory {
        MovieService()
    }
}


private val utilityModule = module {
    factory {
        provideDispatcher()
    }
}


private val domainModule = module {
    single<MovieRepo> {
        MovieRepoImpl(get())
    }

    factory {
        GetMoviesUseCase()
    }

    factory {
        GetSingleMovieUseCase()
    }
}


private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules