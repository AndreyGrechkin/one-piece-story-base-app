package com.defey.onepiecestorybase.domain.usecase.fruit

import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.MangaRepository
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMangaFruitUseCase(
    private val repo: MangaRepository
) : FlowUseCase<Int?, Manga>() {
    override fun execute(parameters: Int?): Flow<Response<Manga>> {
        val param = parameters ?: throw NullPointerException("mangaId can't be null")
        return repo.getManga(param).map { value ->
            value?.let { Response.Success(it) } ?: Response.Failure(
                false,
                null,
                "manga is not found"
            )
        }
    }
}