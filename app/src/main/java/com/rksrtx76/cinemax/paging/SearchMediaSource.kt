package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.util.MediaType
import retrofit2.HttpException
import java.io.IOException

class SearchMediaSource(
    private val api : MediaApiService,
    private val searchParams : String,
    private val includeAdult : Boolean
) : PagingSource<Int, Search>(){
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val nextPage = params.key ?: 1

            val searchMedia = api.multiSearch(
                page = nextPage,
                searchParams = searchParams,
                includeAdult = includeAdult
            )

            LoadResult.Page(
                data = searchMedia.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(searchMedia.results.isEmpty()) null else searchMedia.page + 1
            )
        }catch (e : IOException){
            return LoadResult.Error(e)
        }catch (e : HttpException){
            return LoadResult.Error(e)
        }
    }
}