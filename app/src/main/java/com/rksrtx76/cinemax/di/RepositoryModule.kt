package com.rksrtx76.cinemax.di

import com.rksrtx76.cinemax.data.repository.BookMarkListRepositoryImpl
import com.rksrtx76.cinemax.data.repository.CastRepositoryImpl
import com.rksrtx76.cinemax.data.repository.GenreRepositoryImpl
import com.rksrtx76.cinemax.data.repository.MediaRepositoryImpl
import com.rksrtx76.cinemax.data.repository.PreferenceRepositoryImpl
import com.rksrtx76.cinemax.data.repository.SearchRepositoryImpl
import com.rksrtx76.cinemax.data.repository.VideoRepositoryImpl
import com.rksrtx76.cinemax.domain.repository.BookMarkListRepository
import com.rksrtx76.cinemax.domain.repository.CastRepository
import com.rksrtx76.cinemax.domain.repository.GenreRepository
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.domain.repository.PreferenceRepository
import com.rksrtx76.cinemax.domain.repository.SearchRepository
import com.rksrtx76.cinemax.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMediaRepository(
        mediaRepositoryImpl: MediaRepositoryImpl
    ) : MediaRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ) : SearchRepository

    @Binds
    @Singleton
    abstract fun bindMGenresRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ) : GenreRepository

    @Binds
    @Singleton
    abstract fun bindBookMarkListRepository(
        bookMarkListRepositoryImpl: BookMarkListRepositoryImpl
    ) : BookMarkListRepository

    @Binds
    @Singleton
    abstract fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ) : VideoRepository

    @Binds
    @Singleton
    abstract fun bindCastRepository(
        castRepositoryImpl: CastRepositoryImpl
    ) : CastRepository


    @Binds
    @Singleton
    abstract fun bindPreferenceRepository(
        preferenceRepositoryImpl: PreferenceRepositoryImpl
    ) : PreferenceRepository

}