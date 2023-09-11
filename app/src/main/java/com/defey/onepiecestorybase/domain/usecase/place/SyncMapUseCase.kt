package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SyncMapUseCase(
    private val repo: PlaceRepository
): FlowUseCase<SyncMapParam, Unit>() {
    override fun execute(parameters: SyncMapParam?): Flow<Response<Unit>> {
        return flow {
            val param = parameters ?: throw Exception("Place Id can't be null")
            emit(repo.syncMapById(param.placeId))
        }
    }
}

data class SyncMapParam(
    val placeId: Int
)