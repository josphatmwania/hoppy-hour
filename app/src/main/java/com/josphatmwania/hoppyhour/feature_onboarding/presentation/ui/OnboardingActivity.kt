package com.josphatmwania.hoppyhour.feature_onboarding.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.josphatmwania.hoppyhour.R
import com.josphatmwania.hoppyhour.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val viewModel: OnboardingActivityViewModel by viewModels()
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        lifecycleScope.launchWhenCreated {
            viewModel.showOnboarding.collect {
                Log.d("TAG", "initialize: $it")
            }
        }
        binding.btnGetStarted.setOnClickListener {
            viewModel.updateOnboadingState(false, this)
        }
    }
}