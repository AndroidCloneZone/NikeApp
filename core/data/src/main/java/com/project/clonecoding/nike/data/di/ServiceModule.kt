package com.project.clonecoding.nike.data.di

import com.project.clonecoding.nike.data.remote.BaseUrl
import com.project.clonecoding.nike.data.remote.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getMatchedHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")

                val httpUrl = chain.request().url.newBuilder().build()

                val request = requestBuilder
                    .url(httpUrl)
                    .build()

                chain.proceed(request)
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideProductService(): ProductService {
        val productRetrofit = Retrofit.Builder()
            .baseUrl(BaseUrl.FB_BASE_URL)
            .client(getMatchedHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return productRetrofit.create(ProductService::class.java)
    }
}