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
import com.bumptech.glide.Glide
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
        // use to add progressbar state
        if (event?.isLoading == true) {
            binding.apply {
                // set visibility
                singleBeerProgressbar.visibility = View.VISIBLE
                txvErrorMessage.visibility = View.GONE
                imvBeer.visibility = View.GONE
                txvName.visibility = View.GONE
                txvAlcoholPercentage.visibility = View.GONE
            }
        }
        // use to show error message
        if (event?.message != "") {
            binding.apply {
                // set visibility
                singleBeerProgressbar.visibility = View.GONE
                txvErrorMessage.visibility = View.VISIBLE
                txvErrorMessage.text = event?.message
                imvBeer.visibility = View.GONE
                txvName.visibility = View.GONE
                txvAlcoholPercentage.visibility = View.GONE
            }
        }
        // use to display data
        if (event?.data != null) {
            Log.d("TAG", "onViewCreated: ${event.data}")
            binding.apply {
                // set visibility
                singleBeerProgressbar.visibility = View.GONE
                txvErrorMessage.visibility = View.GONE
                imvBeer.visibility = View.VISIBLE
                txvName.visibility = View.VISIBLE
                txvAlcoholPercentage.visibility = View.VISIBLE
                // set data
                event.data?.let { beer ->
                    Glide.with(requireContext())
                        .load(beer.imageUrl)
                        .into(imvBeer)
                    txvName.text = beer.name
                    txvAlcoholPercentage.text = beer.abv.toString()
                }
            }
        }
    }

}