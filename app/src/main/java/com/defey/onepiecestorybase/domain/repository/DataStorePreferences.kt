package com.defey.onepiecestorybase.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStorePreferences {
    suspend fun saveOnboardingComplete(complete: Boolean)
    fun readOnboardingComplete() : Flow<Boolean>
}