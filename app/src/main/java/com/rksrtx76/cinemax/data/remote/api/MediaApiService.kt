package com.rksrtx76.cinemax.data.remote.api


import com.rksrtx76.cinemax.data.model.MediaDetails
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.data.remote.dto.MediaResponseDto
import com.rksrtx76.cinemax.data.remote.dto.MultiSearchResponseDto
import com.rksrtx76.cinemax.data.remote.dto.VideosDto
import com.rksrtx76.cinemax.util.Constants.API_KEY
import com.rksrtx76.cinemax.util.Constants.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MediaApiService {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ) : GenreResponseDto

    @GET("genre/tv/list")
    suspend fun getTvShowsGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): GenreResponseDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): MediaResponseDto

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaResponseDto

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaResponseDto

    @GET("trending/tv/day")
    suspend fun getTrendingSeries(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): MediaResponseDto

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaDetails

    @GET("tv/{tv_id}")
    suspend fun getTVDetails(
        @Path("tv_id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaDetails

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): MediaResponseDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MediaResponseDto


    @GET("tv/airing_today")
    suspend fun getTVAiringToday(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): MediaResponseDto




    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): CastResponseDto

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCredits(
        @Path("tv_id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): CastResponseDto

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") query: String,
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
    ): MultiSearchResponseDto


    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String = "en-US",
    ) : MediaResponseDto

    @GET("tv/{tv_id}/recommendations")
    suspend fun getRecommendedSeries(
        @Path("tv_id") tvId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String = "en-US",
    ) : MediaResponseDto

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String = "en-US",
    ) : MediaResponseDto

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarSeries(
        @Path("tv_id") tvId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String = "en-US",
    ) : MediaResponseDto

    @GET("movie/{id}/videos")
    suspend fun getMovieVideosList(
        @Path("id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): VideosDto

    @GET("tv/{id}/videos")
    suspend fun getTvVideosList(
        @Path("id") mediaId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): VideosDto
}

