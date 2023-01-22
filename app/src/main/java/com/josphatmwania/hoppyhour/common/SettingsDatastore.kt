package com.josphatmwania.hoppyhour.common

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


private val Context.datastore by preferencesDataStore(
    name = Constants.SHOW_ONBOARD_PREFERENCES
)
class SettingsDatastore(private val context: Context) {
    companion object {
        private val SHOW_ONBOARDING = booleanPreferencesKey(Constants.SHOW_ONBOARDING)
    }

    suspend fun updateOnboadingState(showOnboarding: Boolean, context: Context) {
        context.datastore.edit { preferences ->
            preferences[SHOW_ONBOARDING] = showOnboarding
        }
    }

    val preferenceFlow = context.datastore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SHOW_ONBOARDING] ?: true
        }
}