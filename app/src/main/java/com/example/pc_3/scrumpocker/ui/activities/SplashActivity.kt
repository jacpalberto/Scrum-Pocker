package com.example.pc_3.scrumpocker.ui.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.pc_3.scrumpocker.R

class SplashActivity : AppCompatActivity() {
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        handler.postDelayed({
            startActivity(MainActivity.newIntent(this))
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}