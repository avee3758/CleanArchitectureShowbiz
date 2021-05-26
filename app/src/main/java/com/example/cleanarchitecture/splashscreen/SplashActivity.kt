package com.example.cleanarchitecture.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.home.HomeActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*
import kotlin.concurrent.schedule
@FlowPreview
@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<ImageView>(R.id.splash_image).setImageResource(R.mipmap.ic_launcher)
        Timer().schedule(3000) {
            startActivity(
                Intent(
                    this@SplashActivity,
                    HomeActivity::class.java
                )
            )
            this@SplashActivity.finish()
        }
    }
}