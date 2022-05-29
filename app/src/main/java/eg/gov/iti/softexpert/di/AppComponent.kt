package eg.gov.iti.softexpert.di

import dagger.Component
import eg.gov.iti.softexpert.CarApplication
import javax.inject.Singleton


@Component(modules = [CarProviderModule::class])
@Singleton
interface AppComponent {
    fun inject(app: CarApplication)
}