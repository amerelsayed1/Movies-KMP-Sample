package com.iamer.moviessample.kmp.util


import com.iamer.moviessample.kmp.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getSharedModules())
    }
}