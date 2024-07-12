package lilianisoft.test_task.filmswiki.di

import dagger.Module
import dagger.Provides
import lilianisoft.test_task.filmswiki.BuildConfig
import lilianisoft.test_task.filmswiki.data.network.MoviesApi
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(moviesApi)
    }

    @Provides
    fun provideRequestsApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkhttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest.url.newBuilder()
                .addQueryParameter(
                    name = "api_key",
                    value = BuildConfig.API_KEY
                )
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
    }
}