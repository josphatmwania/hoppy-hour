package com.josphatmwania.hoppyhour.feature_onboarding.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.josphatmwania.hoppyhour.databinding.ActivityOnboardingBinding
import com.josphatmwania.hoppyhour.feature_beer.presentation.ui.MainActivity
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
        viewModel.updateButtonVisibility()
        showGetStartedButton()
        val intent = Intent(this, MainActivity::class.java)
        showOnboarding(intent)
        binding.btnGetStarted.setOnClickListener {
            viewModel.updateOnboadingState(false, this)
            startActivity(intent)
        }
    }

    private fun showGetStartedButton() {
        lifecycleScope.launchWhenCreated {
            viewModel.showGetStartedButton.collect { showGetStartedButton ->
                if (showGetStartedButton) {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        btnGetStarted.visibility = View.VISIBLE
                        imgBeer.visibility = View.VISIBLE
                        txvInfo.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun showOnboarding(intent: Intent) {
        lifecycleScope.launchWhenCreated {
            viewModel.showOnboarding.collect { showOnboarding ->
                if (!showOnboarding) {
                    startActivity(intent)
                }
            }
        }
    }

}