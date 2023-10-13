package com.example.android.dagger.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.android.dagger.MyApplication
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent
            .splashComponent()
            .create()
            .inject(this)
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        splashViewModel.state.observe(this) { state ->
            startActivity(Intent(this@SplashActivity, state.redirectClass))
            finish()
        }
    }


}

