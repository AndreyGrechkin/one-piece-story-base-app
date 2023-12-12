package com.defey.onepiecestorybase.data.repository

import android.content.Context
import coil.ImageLoader
import coil.request.ImageRequest
import com.defey.onepiecestorybase.domain.repository.ImageLoaderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageLoaderRepositoryImpl @Inject constructor(
    val context: Context
) : ImageLoaderRepository {
    override fun getImageSize(url: String): Flow<Pair<Int, Int>> {
        return flow {
            val imageLoader = ImageLoader.Builder(context)
                .crossfade(true)
                .build()

            val request = ImageRequest.Builder(context)
                .data(url)
                .target { drawable ->
                    drawable.intrinsicWidth
                    drawable.intrinsicHeight
                }
                .build()
            val disposable = imageLoader.execute(request)
            val imageSize = Pair(
                disposable.drawable?.intrinsicWidth ?: 0,
                disposable.drawable?.intrinsicHeight ?: 0
            )
            emit(imageSize)
        }
    }
}