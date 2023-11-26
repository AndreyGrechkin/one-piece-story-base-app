package com.defey.onepiecestorybase.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStorePreferences {
    suspend fun saveOnboardingComplete(complete: Boolean)
    fun readOnboardingComplete() : Flow<Boolean>
    suspend fun saveTimeStep(data: String)
    suspend fun saveTimeNow(data: String)
    fun readTimeStep(): Flow<String>
    fun readTimeNow(): Flow<String>

}