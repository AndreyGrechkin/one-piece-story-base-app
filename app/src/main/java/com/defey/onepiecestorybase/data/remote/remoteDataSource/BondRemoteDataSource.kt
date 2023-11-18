package com.defey.onepiecestorybase.data.remote.remoteDataSource

import com.defey.onepiecestorybase.data.remote.api.OnePieceApi
import javax.inject.Inject

interface BondRemoteDataSource {
}

class BondRemoteDataSourceImpl @Inject constructor(
    private val api: OnePieceApi
) : BondRemoteDataSource {}