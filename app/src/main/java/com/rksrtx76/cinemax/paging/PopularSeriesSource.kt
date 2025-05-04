package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularSeriesSource @Inject constructor(
    private val mediaApiService: MediaApiService
) : PagingSource<Int, Media>(){
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try{
            val nextPage = params.key ?: 1
            val popularSeries = mediaApiService.getPopularSeries(nextPage)
            LoadResult.Page(
                data = popularSeries.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(popularSeries.results.isEmpty()) null else popularSeries.page + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}