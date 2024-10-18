package com.syedsaifhossain.datastorepreferenceproject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DatastoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStoreManager")

    companion object{
        val EMAIL = stringPreferencesKey("email")
        val PADSSWORD = stringPreferencesKey("password")
        val CONFIRMPASSWORD = stringPreferencesKey("confirmPassword")
    }

    // Save a string value in the DataStore

    suspend fun saveString(email: String, password: String,confirmPassword:String) {

        context.dataStore.edit { preferences->
            preferences[EMAIL] = email
            preferences[PADSSWORD] = password
            preferences[CONFIRMPASSWORD] = confirmPassword

        }
    }

    fun getEmail(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL]?:"Email is incorrect"
        }
    }

    fun getPassword(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PADSSWORD]?:"Password is incorrect"
        }
    }

    fun getConfirmPassword(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[CONFIRMPASSWORD]?:"Password not match"
        }
    }

}





