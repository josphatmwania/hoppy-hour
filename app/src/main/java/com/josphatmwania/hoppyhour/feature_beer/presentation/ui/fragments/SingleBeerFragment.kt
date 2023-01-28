package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.josphatmwania.hoppyhour.databinding.FragmentSingleBeerBinding
import com.josphatmwania.hoppyhour.feature_beer.presentation.events.BeerEvent
import com.josphatmwania.hoppyhour.feature_beer.presentation.ui.viewmodels.SingleBeerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleBeerFragment : Fragment() {
    private lateinit var binding: FragmentSingleBeerBinding
    private val viewModel: SingleBeerViewModel by viewModels()
    private val args: SingleBeerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleBeerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.findBeer(args.beerId)
            viewModel.beer.collect { event ->
                showEvent(event)
            }
        }
    }

    private fun showEvent(event: BeerEvent?) {
        if (event?.isLoading == true) {
            // use to add progressbar state
            binding.apply {
                singleBeerProgressbar.visibility = View.VISIBLE
                txvErrorMessage.visibility = View.GONE
            }
        }
        if (event?.message != "") {
            // use to show error message
            binding.apply {
                singleBeerProgressbar.visibility = View.GONE
                txvErrorMessage.visibility = View.VISIBLE
                txvErrorMessage.text = event?.message
            }
        }
        if (event?.data != null) {
            // use to display data
            Log.d("TAG", "onViewCreated: ${event.data}")
            binding.apply {
                singleBeerProgressbar.visibility = View.GONE
                txvErrorMessage.visibility = View.GONE
            }
        }
    }

}