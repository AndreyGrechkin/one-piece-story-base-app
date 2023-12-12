package com.defey.onepiecestorybase.domain.usecase.actionPlace

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.ImageLoaderRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetImageSizeUseCase(
    private val repo: ImageLoaderRepository
) : FlowUseCase<String?, Pair<Int, Int>>() {
    override fun execute(parameters: String?): Flow<Response<Pair<Int, Int>>> {
        val param = parameters ?: throw NullPointerException("url can't be null")
        return repo.getImageSize(param).map {
            Response.Success(it)
        }
    }
}