package com.defey.onepiecestorybase.domain.usecase.place

import android.util.Log
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.usecase.FlowUseCase
import com.defey.onepiecestorybase.presentation.utils.asLocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class GetNextTimeUseCase(
    private val repo: DataStorePreferences
) : FlowUseCase<Nothing?, Long>() {
    override fun execute(parameters: Nothing?): Flow<Response<Long>> {
        val dateNow = LocalDateTime.now()
        return repo.readTimeStep().map { nextDate ->
            val countDown = ChronoUnit.SECONDS.between(dateNow, nextDate.asLocalDateTime())
            Log.d("MyLog", "Use time $countDown, next Day: $nextDate, now: $dateNow")
            Response.Success(countDown)
        }
    }
}


