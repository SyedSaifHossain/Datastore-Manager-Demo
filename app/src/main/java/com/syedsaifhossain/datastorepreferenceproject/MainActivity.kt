package com.syedsaifhossain.datastorepreferenceproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.syedsaifhossain.datastorepreferenceproject.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var datastoreManager: DatastoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        datastoreManager = DatastoreManager(this)
        binding.save.setOnClickListener{

            lifecycleScope.launch {
                datastoreManager.saveString(

                    binding.email.text.toString(),
                    binding.password.text.toString(),
                    binding.confirmPassword.text.toString()
                )
            }

            lifecycleScope.launch {
                datastoreManager.getEmail().collect {
                    binding.displayEmail.text = it.toString()
                }
            }

                lifecycleScope.launch {
                    datastoreManager.getPassword().collect {
                        binding.displayPassword.text = it.toString()
                }
        }

            lifecycleScope.launch {
                datastoreManager.getConfirmPassword().collect {
                    binding.displayConfirmPassword.text = it.toString()
                }
            }
    }
    }
}