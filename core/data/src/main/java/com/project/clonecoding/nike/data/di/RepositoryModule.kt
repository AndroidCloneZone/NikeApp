package com.project.clonecoding.nike.data.di

import com.project.clonecoding.nike.data.local.db.NikeDatabase
import com.project.clonecoding.nike.data.remote.service.ProductService
import com.project.clonecoding.nike.data.repository.HomeRepositoryImpl
import com.project.clonecoding.nike.data.repository.ProductRepositoryImpl
import com.project.clonecoding.nike.domain.repository.HomeRepository
import com.project.clonecoding.nike.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(nikeDatabase: NikeDatabase): HomeRepository {
        return HomeRepositoryImpl(nikeDatabase = nikeDatabase)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): ProductRepository =
        ProductRepositoryImpl(productService = productService)
}