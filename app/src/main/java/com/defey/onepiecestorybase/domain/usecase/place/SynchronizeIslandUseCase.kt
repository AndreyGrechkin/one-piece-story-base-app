package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.IslandRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SynchronizeIslandUseCase(
    private val repo: IslandRepository
) : FlowUseCase<Nothing?, Unit>() {
    override fun execute(parameters: Nothing?): Flow<Response<Unit>> {
        return flow {
            emit(repo.synchronizeIsland())
        }
    }
}