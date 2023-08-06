package com.defey.onepiecestorybase.data.local.sharedPref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.presentation.utils.Constants.ONBOARDING_PREF_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStorePreferencesImpl(context: Context, name: String):DataStorePreferences {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    private val prefDataStore = context.preferencesDataStore

    private object PreferenceKey {
      val onboardingKey = booleanPreferencesKey(ONBOARDING_PREF_KEY)
    }


    override suspend fun saveOnboardingComplete(complete: Boolean) {
        prefDataStore.edit { pref ->
            pref[PreferenceKey.onboardingKey] = complete
        }
    }

    override fun readOnboardingComplete(): Flow<Boolean> {
        return prefDataStore.data
            .catch { exception ->
                if (exception is IOException)
                    emit(emptyPreferences())
                else
                    throw exception
            }
            .map {pref ->
                val onboardingState = pref[PreferenceKey.onboardingKey] ?: false
                onboardingState
            }
    }

}