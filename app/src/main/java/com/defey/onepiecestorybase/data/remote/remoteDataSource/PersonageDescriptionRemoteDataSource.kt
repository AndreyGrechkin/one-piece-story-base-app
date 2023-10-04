package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import javax.inject.Inject

interface PersonageDescriptionRemoteDataSource {
}

class PersonageDescriptionRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : PersonageDescriptionRemoteDataSource {

}