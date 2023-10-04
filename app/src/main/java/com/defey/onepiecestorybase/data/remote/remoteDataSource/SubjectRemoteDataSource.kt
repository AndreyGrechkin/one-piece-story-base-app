package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import javax.inject.Inject

interface SubjectRemoteDataSource {
}

class  SubjectRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
): SubjectRemoteDataSource {

}