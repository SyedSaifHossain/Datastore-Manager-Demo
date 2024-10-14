package com.syedsaifhossain.datastorepreferenceproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            saveCredentials(inputUserId, inputPassword)
        }


    }
}
private suspend fun saveCredentials(userId: String, password: String) {
    val datastoreManager = DatastoreManager(this)
    datastoreManager.saveString("user_id", userId)
    datastoreManager.saveString("password", password)
}