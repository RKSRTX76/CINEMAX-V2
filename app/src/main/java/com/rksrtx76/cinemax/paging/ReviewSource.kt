package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.data.model.Review
import com.rksrtx76.cinemax.util.MediaType
import retrofit2.HttpException
import java.io.IOException

class ReviewSource(
    private val api : MediaApiService,
    private val mediaId : Int,
    private val mediaType : MediaType
) : PagingSource<Int, Review>(){
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val nextPage = params.key ?: 1

            val mediaReview = api.getMediaReviews(
                page = nextPage,
                mediaId = mediaId,
                mediaPath = if(mediaType == MediaType.MOVIE) "movie" else "tv"
            )

            LoadResult.Page(
                data = mediaReview.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(mediaReview.results.isEmpty()) null else mediaReview.page + 1
            )
        }catch (e : IOException){
            return LoadResult.Error(e)
        }catch (e : HttpException){
            return LoadResult.Error(e)
        }
    }
}