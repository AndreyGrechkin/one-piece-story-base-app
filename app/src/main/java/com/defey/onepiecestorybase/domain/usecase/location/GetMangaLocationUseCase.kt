package com.defey.onepiecestorybase.domain.usecase.location

import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMangaLocationUseCase(
    private val repo: MangaRepository
) : FlowUseCase<Int?, List<Manga>>() {
    override fun execute(parameters: Int?): Flow<Response<List<Manga>>> {
        val param = parameters ?: throw NullPointerException("locationId can't be null")
        return repo.getMangaInPlace(param).map { value ->
            Response.Success(value)
        }
    }
}