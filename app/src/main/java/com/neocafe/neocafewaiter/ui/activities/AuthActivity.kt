package com.neocafe.neocafewaiter.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neocafe.neocafewaiter.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}