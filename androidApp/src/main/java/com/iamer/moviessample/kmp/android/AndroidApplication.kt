package com.iamer.moviessample.kmp.android

import android.app.Application
import com.iamer.moviessample.kmp.android.di.appModule
import com.iamer.moviessample.kmp.di.getSharedModules
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }

}