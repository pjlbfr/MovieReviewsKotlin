package com.moviereviewskotlin.di.modules

import com.google.gson.Gson
import com.moviereviewskotlin.BuildConfig
import com.moviereviewskotlin.retrofit.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val TIMEOUT_SEC: Long = 20
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()

        val gsonConverterFactory = GsonConverterFactory.create(Gson())

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @JvmStatic
    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}