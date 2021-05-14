package com.hfad.cookbook

import android.app.Application
import com.hfad.cookbook.di.dataModule
import com.hfad.cookbook.di.uiModule
import com.hfad.cookbook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CookbookApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CookbookApp)
            modules(viewModelModule, dataModule, uiModule)
        }
    }
}