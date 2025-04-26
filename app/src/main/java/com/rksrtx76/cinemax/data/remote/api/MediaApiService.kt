package com.rksrtx76.cinemax.data.remote.api


import com.rksrtx76.cinemax.BuildConfig
import com.rksrtx76.cinemax.data.remote.dto.CastResponseDto
import com.rksrtx76.cinemax.data.remote.dto.GenreResponseDto
import com.rksrtx76.cinemax.data.remote.dto.MediaResponseDto
import com.rksrtx76.cinemax.data.remote.dto.MultiSearchResponseDto
import com.rksrtx76.cinemax.data.remote.dto.ReviewResponseDto
import com.rksrtx76.cinemax.data.remote.dto.VideosDto
import com.rksrtx76.cinemax.util.Constants.api_key
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MediaApiService {
    /**  Movies  **/
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String = api_key,
    ) : CastResponseDto

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : GenreResponseDto

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") searchParams : String,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en",
        @Query("include_adult") includeAdult : Boolean = true
    ) : MultiSearchResponseDto



    /**  Tv Shows  **/
    /* [Note]: **MovieResponse** and **TvShowResponse** attributes are combined based
     on the fact that you can extract them at runtime by fetching what you need! */

    @GET("genre/tv/list")
    suspend fun getTvShowGenres(
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : GenreResponseDto

    @GET("tv/{tv_id}/credits")
    suspend fun getTvShowCast(
        @Path("tv_id") showId : Int,
        @Query("api_key") apiKey : String = api_key,
    ) : CastResponseDto

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") showId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    @GET("tv/{tv_id}/recommendations")
    suspend fun getRecommendedTvShows(
        @Path("tv_id") showId : Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : MediaResponseDto

    /** Reviews*/
    @GET("*{film_path}/{film_id}/reviews?")
    suspend fun getMediaReviews(
        @Path("film_id") mediaId : Int,
        @Path("film_path") mediaPath : String,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey : String = api_key,
        @Query("language") language: String = "en"
    ) : ReviewResponseDto

    /** Videos **/
    @GET("{type}/{id}/videos")
    suspend fun getVideosList(
        @Path("type") type: String, // "movie" or "tv"
        @Path("id") mediaId: Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = api_key,
        @Query("language") language: String = "en"
    ): VideosDto?
}

