package com.defey.onepiecestorybase.domain.usecase

import com.defey.onepiecestorybase.domain.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

abstract class FlowUseCase<in P, R> {
    operator fun invoke(parameters: P? = null): Flow<Response<R>> = execute(parameters)
        .onStart {
            emit(Response.Loading)
        }

    protected abstract fun execute(parameters: P?): Flow<Response<R>>
}