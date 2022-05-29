package eg.gov.iti.softexpert

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import eg.gov.iti.softexpert.di.AppComponent
import eg.gov.iti.softexpert.di.DaggerAppComponent


@HiltAndroidApp

class CarApplication : Application() {

    companion object {
        lateinit var instance: CarApplication
            private set
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .build()
        appComponent.inject(this)
    }
}