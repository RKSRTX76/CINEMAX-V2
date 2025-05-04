package com.rksrtx76.cinemax.di

import android.app.Application
import androidx.room.Room
import com.rksrtx76.cinemax.data.local.BookMarkDataBase
import com.rksrtx76.cinemax.data.preferences.UserPreferences
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideMediaApiService(): MediaApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MediaApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideBookMarkList(application : Application) : BookMarkDataBase{
        return Room.databaseBuilder(
            application,
            BookMarkDataBase::class.java,
            "bookmark_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDataStore(application: Application) : UserPreferences{
        return UserPreferences(application.applicationContext)
    }

//    @Singleton
//    @Provides
//    fun providesUseCases(repository: Repository) : UseCases{
//        return UseCases(
//            addToBookmarkedUseCase = AddToBookmarkedUseCase(repository),
//         deleteAllFromBookmarkedUseCase = DeleteAllFromBookmarkedUseCase(repository),
//         removeFromBookmarkedUseCase = RemoveFromBookmarkedUseCase(repository),
//         isExistUseCase = IsExistUseCase(repository),
//         getBookmarkListUseCase = GetBookmarkListUseCase(repository),
//
//         getMovieDetailsUseCase = GetMovieDetailsUseCase(repository),
//         getTVDetailsUseCase = GetTVDetailsUseCase(repository),
//         movieCastDetailsUseCase = MovieCastDetailsUseCase(repository),
//         getMoviesGenreUseCase = GetMovieGenresUseCase(repository),
//         getSeriesGenreUseCase = GetSeriesGenresUseCase(repository),
//         getPopularMoviesUseCase = GetPopularMoviesUseCase(repository),
//         getPopularSeriesUseCase = GetPopularSeriesUseCase(repository),
//         getABookmarkedUseCase = GetABookmarkedUseCase(repository),
//         getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository),
//         getTopRatedSeriesUseCase = GetTopRatedSeriesUseCase(repository),
//         getAiringTodaySeriesUseCase = GetAiringTodaySeriesUseCase(repository)
//         getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(repository),
//         multiSearchUseCase = MultiSearchUseCase(repository),
//         getTrendingMoviesUseCase = GetTrendingMoviesUseCase(repository),
//         getTrendingSeriesUseCase = GetTrendingSeriesUseCase(repository),
//         getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(repository),
//         getSimilarMoviesUseCase = GetSimilarMoviesUseCase(repository),
//         getSimilarSeriesUseCase = GetSimilarSeriesUseCase(repository),
//         getRecommendedMoviesUseCase = GetRecommendedMoviesUseCase(repository),
//         getRecommendedSeriesUseCase = GetRecommendedSeriesUseCase(repository)
//        )
//    }

}