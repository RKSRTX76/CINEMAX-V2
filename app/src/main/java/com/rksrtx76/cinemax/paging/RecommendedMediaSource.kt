package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.util.MediaType
import retrofit2.HttpException
import java.io.IOException

class RecommendedMediaSource(
    private val api : MediaApiService,
    private val mediaId : Int,
    private val mediaType : MediaType
) : PagingSource<Int, Media>(){
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val nextPage = params.key ?: 1

            val recommendedMedia = if(mediaType == MediaType.MOVIE){
                api.getRecommendedMovies(page = nextPage, movieId = mediaId)
            }else{
                api.getRecommendedTvShows(page = nextPage, showId = mediaId)
            }

            LoadResult.Page(
                data = recommendedMedia.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(recommendedMedia.results.isEmpty()) null else recommendedMedia.page + 1
            )
        }catch (e : IOException){
            return LoadResult.Error(e)
        }catch (e : HttpException){
            return LoadResult.Error(e)
        }
    }
}
