package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecommendedMoviesSource @Inject constructor(
    private val mediaApiService: MediaApiService,
    private val mediaId: Int
) : PagingSource<Int, Media>(){
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try{
            val nextPage = params.key ?: 1
            val recommendedMovies = mediaApiService.getRecommendedMovies(mediaId, nextPage)
            LoadResult.Page(
                data = recommendedMovies.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(recommendedMovies.results.isEmpty()) null else recommendedMovies.page + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}