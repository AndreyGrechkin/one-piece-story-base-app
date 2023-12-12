package com.defey.onepiecestorybase.domain.repository

import kotlinx.coroutines.flow.Flow

interface ImageLoaderRepository {
    fun getImageSize(url: String): Flow<Pair<Int, Int>>
}