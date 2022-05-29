package eg.gov.iti.softexpert.di

import androidx.databinding.ktx.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eg.gov.iti.softexpert.business.dataSource.CarApi
import eg.gov.iti.softexpert.business.dataSource.abstraction.CarDataSource
import eg.gov.iti.softexpert.business.dataSource.impl.CarDataSourceImpl
import eg.gov.iti.softexpert.business.repo.abstraction.CarReository
import eg.gov.iti.softexpert.business.repo.impl.CarRepositiryImpl
import eg.gov.iti.softexpert.business.useCase.abtraction.CarUseCase
import eg.gov.iti.softexpert.business.useCase.impl.CarUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class CarProviderModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://demo1585915.mockable.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): CarApi =
        retrofit.create(CarApi::class.java)

    @Provides
    @Singleton
    fun provideCarApiDataSource(carApi: CarApi): CarDataSource =
        CarDataSourceImpl(carApi)

    @Provides
    @Singleton
    fun provideCarApiRepo(
        carDataSource: CarDataSource,

        ): CarReository =
        CarRepositiryImpl(carDataSource)

    @Provides
    @Singleton
    fun providesCarUseCase(
        carRepository: CarReository,

        ): CarUseCase =
        CarUseCaseImpl(carRepository)


}