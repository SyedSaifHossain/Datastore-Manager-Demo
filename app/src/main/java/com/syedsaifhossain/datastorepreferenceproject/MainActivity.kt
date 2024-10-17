package com.syedsaifhossain.datastorepreferenceproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var confirmPassword : EditText
    private lateinit var saveButton : Button
    private lateinit var displayEmail : TextView
    private lateinit var displayPassword : TextView
    private lateinit var displayConfirmPassword : TextView

    private lateinit var datastoreManager: DatastoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        saveButton = findViewById(R.id.save)
        displayEmail = findViewById(R.id.displayEmail)
        displayPassword = findViewById(R.id.displayPassword)
        displayConfirmPassword = findViewById(R.id.displayConfirmPassword)
        datastoreManager = DatastoreManager(this)
        saveButton.setOnClickListener{
            lifecycleScope.launch {
                saveCredentials(
                    email.text.toString(),
                    password.text.toString(),
                    confirmPassword.text.toString(),

                )

                retrieveCredentials()
            }
        }
    }

    suspend fun saveCredentials(email: String, password: String, confirmPassword: String) {
        datastoreManager.saveString("email", email)
        datastoreManager.saveString("password", password)
        datastoreManager.saveString("confirmPassword", confirmPassword)
    }
    private fun retrieveCredentials() {
        lifecycleScope.launch {
            datastoreManager.getString("email", "").collect { emailValue ->
                displayEmail.text = emailValue
            }

            datastoreManager.getString("password", "").collect { passwordValue ->
                displayPassword.text = passwordValue

            }
            datastoreManager.getString("confirmPassword", "").collect { confirmPasswordValue ->
                displayConfirmPassword.text = confirmPasswordValue
            }
        }
    }

}




