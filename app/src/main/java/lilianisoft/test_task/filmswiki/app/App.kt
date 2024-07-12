package lilianisoft.test_task.filmswiki.app

import android.app.Application
import lilianisoft.test_task.filmswiki.di.AppComponent
import lilianisoft.test_task.filmswiki.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}