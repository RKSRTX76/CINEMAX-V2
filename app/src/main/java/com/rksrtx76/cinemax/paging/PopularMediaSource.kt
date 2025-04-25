package com.rksrtx76.cinemax.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.util.MediaType
import retrofit2.HttpException
import java.io.IOException

class PopularMediaSource(
    private val api : MediaApiService,
    private val mediaType : MediaType
) : PagingSource<Int, Media>(){
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val nextPage = params.key ?: 1

            val popularMedia = if(mediaType == MediaType.MOVIE){
                api.getPopularMovies(page = nextPage)
            }else{
                api.getPopularTvShows(page = nextPage)
            }

            LoadResult.Page(
                data = popularMedia.results,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(popularMedia.results.isEmpty()) null else popularMedia.page + 1
            )
        }catch (e : IOException){
            return LoadResult.Error(e)
        }catch (e : HttpException){
            return LoadResult.Error(e)
        }
    }
}