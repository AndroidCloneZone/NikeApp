package com.project.clonecoding.nike.domain.di

import com.project.clonecoding.nike.domain.repository.HomeRepository
import com.project.clonecoding.nike.domain.usecase.AddNewsCommentUseCase
import com.project.clonecoding.nike.domain.usecase.GetNewsCommentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideAddNewsCommentUseCase(
        homeRepository: HomeRepository,
    ) = AddNewsCommentUseCase(homeRepository = homeRepository)

    @Provides
    @Singleton
    fun provideGetNewsCommentUseCase(homeRepository: HomeRepository) =
        GetNewsCommentUseCase(homeRepository = homeRepository)
}