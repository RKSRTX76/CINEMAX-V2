package com.rksrtx76.cinemax.paging

import androidx.compose.ui.res.stringResource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rksrtx76.CINEMAX.model.Media
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.data.model.Video
import com.rksrtx76.cinemax.data.remote.api.MediaApiService
import com.rksrtx76.cinemax.util.MediaType
import retrofit2.HttpException
import java.io.IOException

class VideoMediaSource(
    private val api : MediaApiService,
    private val mediaId : Int,
    private val mediaType : MediaType
) : PagingSource<Int, Video>(){
    override fun getRefreshKey(state: PagingState<Int, Video>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        return try {
            val nextPage = params.key ?: 1

            val video = if(mediaType == MediaType.MOVIE){
                api.getVideosList(mediaId = mediaId, type = "movie")
            }else{
                api.getVideosList(mediaId = mediaId, type = "tv")
            }

            LoadResult.Page(
                data = video?.results ?: emptyList(),
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if (video?.results.isNullOrEmpty()) null else video?.page?.plus(1)
            )
        }catch (e : IOException){
            return LoadResult.Error(e)
        }catch (e : HttpException){
            return LoadResult.Error(e)
        }
    }
}