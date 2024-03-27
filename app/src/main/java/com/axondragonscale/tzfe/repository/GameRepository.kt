package com.axondragonscale.tzfe.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.axondragonscale.tzfe.data.GameState
import com.axondragonscale.tzfe.moshi.MoshiProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 11/03/23
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("tzfe")

class GameRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val gameStateKey = stringPreferencesKey("gameState")

    suspend fun getSavedGameState(): GameState? {
        val gameStateFlow = context.dataStore.data.map {  preferences ->
            preferences[gameStateKey]?.let { MoshiProvider.gameStateAdapter.fromJson(it) }
        }
        return gameStateFlow.firstOrNull()
    }

    suspend fun saveGameState(gameState: GameState) {
        context.dataStore.edit { preferences ->
            preferences[gameStateKey] = MoshiProvider.gameStateAdapter.toJson(gameState)
        }
    }

}
