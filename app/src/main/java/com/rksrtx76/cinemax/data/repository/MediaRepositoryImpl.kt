package com.rksrtx76.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.domain.repository.MediaRepository
import com.rksrtx76.cinemax.paging.AiringTodaySource
import com.rksrtx76.cinemax.paging.NowPlayingMoviesSource
import com.rksrtx76.cinemax.paging.PopularMoviesSource
import com.rksrtx76.cinemax.paging.PopularSeriesSource
import com.rksrtx76.cinemax.paging.RecommendedMoviesSource
import com.rksrtx76.cinemax.paging.RecommendedSeriesSource
import com.rksrtx76.cinemax.paging.SimilarMoviesSource
import com.rksrtx76.cinemax.paging.SimilarSeriesSource
import com.rksrtx76.cinemax.paging.TopRatedMoviesSource
import com.rksrtx76.cinemax.paging.TopRatedSeriesSource
import com.rksrtx76.cinemax.paging.TrendingMovieSource
import com.rksrtx76.cinemax.paging.TrendingSeriesSource
import com.rksrtx76.cinemax.paging.UpcomingMoviesSource
import com.rksrtx76.cinemax.util.MediaType
import com.rksrtx76.cinemax.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepositoryImpl @Inject constructor(
    private val mediaApiService: MediaApiService
) : MediaRepository {
    override suspend fun getMovieDetails(mediaId: Int): Resource<MediaDetails> {
        val response = try{
            mediaApiService.getMovieDetails(mediaId)
        }catch (e: IOException) {
            return Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Error("Server returned an error: ${e.message()}")
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        Timber.d("Movie Details $response")
        return Resource.Success(response)
    }

    override suspend fun getTVDetails(mediaId: Int): Resource<MediaDetails> {
        val response = try{
            mediaApiService.getTVDetails(mediaId)
        }catch (e: IOException) {
            return Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Error("Server returned an error: ${e.message()}")
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        Timber.d("TV Details $response")
        return Resource.Success(response)
    }

    override fun getTrendingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TrendingMovieSource(mediaApiService)
            }
        ).flow
    }

    override fun getTrendingSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TrendingSeriesSource(mediaApiService)
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PopularMoviesSource(mediaApiService)
            }
        ).flow
    }

    override fun getPopularSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PopularSeriesSource(mediaApiService)
            }
        ).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TopRatedMoviesSource(mediaApiService)
            }
        ).flow
    }

    override fun getTopRatedSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TopRatedSeriesSource(mediaApiService)
            }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NowPlayingMoviesSource(mediaApiService)
            }
        ).flow
    }

    override fun getAiringTodaySeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                AiringTodaySource(mediaApiService)
            }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                UpcomingMoviesSource(mediaApiService)
            }
        ).flow
    }

    override fun getSimilarMovies(mediaId : Int): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SimilarMoviesSource(mediaApiService, mediaId)
            }
        ).flow
    }

    override fun getSimilarSeries(mediaId: Int): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SimilarSeriesSource(mediaApiService, mediaId)
            }
        ).flow
    }

    override fun getRecommendedMovies(mediaId: Int): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                RecommendedMoviesSource(mediaApiService, mediaId)
            }
        ).flow
    }

    override fun getRecommendedSeries(mediaId: Int): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                RecommendedSeriesSource(mediaApiService, mediaId)
            }
        ).flow
    }

}