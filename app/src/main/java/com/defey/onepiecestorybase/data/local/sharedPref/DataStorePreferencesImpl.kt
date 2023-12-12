package com.defey.onepiecestorybase.data.local.sharedPref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.presentation.utils.Constants.LAST_MANGA_KEY
import com.defey.onepiecestorybase.presentation.utils.Constants.ONBOARDING_PREF_KEY
import com.defey.onepiecestorybase.presentation.utils.Constants.TIME_NOW_PREF_KEY
import com.defey.onepiecestorybase.presentation.utils.Constants.TIME_STEP_PREF_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferencesImpl(context: Context, name: String) : DataStorePreferences {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    private val prefDataStore = context.preferencesDataStore

    private object PreferenceKey {
        val onboardingKey = booleanPreferencesKey(ONBOARDING_PREF_KEY)
        val timeStepKey = stringPreferencesKey(TIME_STEP_PREF_KEY)
        val timeNowKey = stringPreferencesKey(TIME_NOW_PREF_KEY)
        val lastManga = intPreferencesKey(LAST_MANGA_KEY)
    }


    override suspend fun saveOnboardingComplete(complete: Boolean) {
        prefDataStore.edit { pref ->
            pref[PreferenceKey.onboardingKey] = complete
        }
    }

    override fun readOnboardingComplete(): Flow<Boolean> {
        return prefDataStore.data
            .map { pref ->
                val onboardingState = pref[PreferenceKey.onboardingKey] ?: false
                onboardingState
            }
    }

    override suspend fun saveTimeStep(data: String) {
        prefDataStore.edit { pref ->
            pref.clear()
            pref[PreferenceKey.timeStepKey] = data
        }
    }

    override suspend fun saveTimeNow(data: String) {
        prefDataStore.edit { pref ->
            pref.clear()
            pref[PreferenceKey.timeNowKey] = data
        }
    }

    override fun readTimeStep(): Flow<String> {
        return prefDataStore.data
            .map { pref ->
                pref[PreferenceKey.timeStepKey] ?: ""
            }
    }

    override fun readTimeNow(): Flow<String> {
        return prefDataStore.data
            .map { pref ->
                pref[PreferenceKey.timeNowKey] ?: ""
            }
    }

    override suspend fun saveLastManga(id: Int) {
        prefDataStore.edit { pref ->
            pref.clear()
            pref[PreferenceKey.lastManga] = id
        }
    }

    override fun readLastManga(): Flow<Int?> {
        return prefDataStore.data
            .map { pref ->
                pref[PreferenceKey.lastManga]
            }
    }
}