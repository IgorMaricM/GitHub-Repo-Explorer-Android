package com.im.githubrepoexplorer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        } else {
//            Timber.plant(object : Timber.Tree() {
//                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
//                    //Do nothing for release builds
//                    //Log.d(tag, message)
//                }
//            })
//        }
    }

}