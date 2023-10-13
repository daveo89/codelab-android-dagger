package com.example.android.dagger.splash.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.splash.ui.LaunchState.UserLoggedIn
import com.example.android.dagger.splash.ui.LaunchState.UserNotRegistered
import com.example.android.dagger.splash.ui.LaunchState.UserRegisteredAndNotLoggedIn
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

@ActivityScope
class SplashViewModel @Inject constructor(private val userManager: UserManager) {

    private val _state = MutableLiveData<LaunchState>()
    val state: LiveData<LaunchState> = _state

    init {
        setupInitialState()
    }

    private fun setupInitialState() {
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                // user not registered -> go to RegistrationActivity
                _state.value = UserNotRegistered(redirectClass = RegistrationActivity::class.java)
            } else {
                // user registered and not logged in -> go to LoginActivity
                _state.value =
                    UserRegisteredAndNotLoggedIn(redirectClass = LoginActivity::class.java)
            }
        } else {
            // user logged in -> go to MainActivity
            _state.value = UserLoggedIn(redirectClass = MainActivity::class.java)
        }
    }
}

sealed interface LaunchState {

    val redirectClass: Class<out AppCompatActivity>

    data class UserLoggedIn(override val redirectClass: Class<out AppCompatActivity>) :
        LaunchState {
    }

    data class UserNotRegistered(override val redirectClass: Class<out AppCompatActivity>) :
        LaunchState

    data class UserRegisteredAndNotLoggedIn(override val redirectClass: Class<out AppCompatActivity>) :
        LaunchState
}