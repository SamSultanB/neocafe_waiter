package com.neocafe.neocafewaiter.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neocafe.neocafewaiter.databinding.ActivitySplashScreenBinding


@SuppressLint("CustomSplashScreen")
//todo:remove and make it fragment

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startIcon.alpha = 0f
        binding.startIcon.animate().setDuration(2500).alpha(1f).withEndAction{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}