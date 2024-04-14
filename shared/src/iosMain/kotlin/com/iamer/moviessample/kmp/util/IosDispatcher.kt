package com.iamer.moviessample.kmp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IosDispatcher : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}


internal actual fun provideDispatcher(): Dispatcher = IosDispatcher()