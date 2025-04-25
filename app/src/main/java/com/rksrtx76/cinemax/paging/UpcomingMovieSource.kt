package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import retrofit2.HttpException

class UpcomingMovieSource(
    private val api : MediaApiService
) : PagingSource<Int, Media>(){
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try{
            val nextPage = params.key ?: 1
            val upcomingMovies = api.getUpcomingMovies(page = nextPage)

            LoadResult.Page(
                data = upcomingMovies.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(upcomingMovies.results.isEmpty()) null else upcomingMovies.page + 1
            )
        }catch (e: java.io.IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}