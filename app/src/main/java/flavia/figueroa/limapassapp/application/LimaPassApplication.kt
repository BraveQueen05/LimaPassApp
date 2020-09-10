package flavia.figueroa.limapassapp.application

import android.app.Application
import flavia.figueroa.limapassapp.BuildConfig
import flavia.figueroa.limapassapp.di.viewModulesModule
import flavia.figueroa.repository.local.preferences.di.preferencesModule
import flavia.figueroa.repository.network.di.networkModule
import flavia.figueroa.repository.network.di.retrofitModule
import flavia.figueroa.repository.network.utils.BASE_URL
import flavia.figueroa.usecases.di.usecasesModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LimaPassApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LimaPassApplication)
            modules(
                arrayListOf(
                    viewModulesModule,
                    preferencesModule,
                    networkModule,
                    retrofitModule,
                    usecasesModule
                )
            )
        }
        getKoin().setProperty(BASE_URL, BuildConfig.BASE_URL)
    }
}