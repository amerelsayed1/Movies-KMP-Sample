package com.iamer.moviessample.kmp.android.di

import com.iamer.moviessample.kmp.android.detail.DetailViewModel
import com.iamer.moviessample.kmp.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}