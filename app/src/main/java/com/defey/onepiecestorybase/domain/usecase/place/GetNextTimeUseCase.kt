package com.defey.onepiecestorybase.domain.usecase.place

import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import com.defey.onepiecestorybase.presentation.utils.asLocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import java.time.temporal.ChronoUnit

class GetNextTimeUseCase(
    private val repo: DataStorePreferences
) : FlowUseCase<Nothing?, Long>() {
    override fun execute(parameters: Nothing?): Flow<Response<Long>> {
        return repo.readTimeStep().zip(repo.readTimeNow()) { nextDate, nowKey ->
            val countDown =
                ChronoUnit.SECONDS.between(nowKey.asLocalDateTime(), nextDate.asLocalDateTime())
            Response.Success(countDown)
        }
    }
}


