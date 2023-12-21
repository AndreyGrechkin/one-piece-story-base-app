package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.BondRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SynchronizeBondUseCase @Inject constructor(
    private val repo: BondRepository
) : FlowUseCase<Int?, Unit>() {
    override fun execute(parameters: Int?): Flow<Response<Unit>> {
        val param = parameters ?: throw Exception("Place Id can't be null")
        return flow {
            emit(repo.syncBondByPlace(param))
        }
    }
}