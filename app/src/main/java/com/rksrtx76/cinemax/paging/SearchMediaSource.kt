package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Search
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMediaSource @Inject constructor(
    private val mediaApiService: MediaApiService,
    private val query : String
) : PagingSource<Int, Search>(){
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val nextPage = params.key ?: 1
            val multiSearch = mediaApiService.multiSearch(query = query, nextPage)
            LoadResult.Page(
                data = multiSearch.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (multiSearch.results.isEmpty()) null else multiSearch.page + 1
            )

        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}

