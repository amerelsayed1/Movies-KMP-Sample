package com.iamer.moviessample.kmp.util

import kotlinx.coroutines.CoroutineDispatcher

internal interface Dispatcher {
    val io: CoroutineDispatcher
}


internal expect fun provideDispatcher(): Dispatcher