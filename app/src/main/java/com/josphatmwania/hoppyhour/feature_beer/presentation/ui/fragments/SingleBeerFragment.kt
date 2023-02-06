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
            // set visibility
            loadingStateVisibility(binding)
        }
        // use to show error message
        if (event?.message != "") {
            binding.apply {
                txvErrorMessage.text = event?.message
                // set visibility
                messageStateVisibility(this)
            }
        }
        // use to display data
        if (event?.data != null) {
            binding.apply {
                // set visibility
                dataStateVisibility(this)
                // set data
                event.data?.let { beer ->
                    Glide.with(requireContext())
                        .load(beer.imageUrl)
                        .into(imvBeer)
                    txvName.text = beer.name
                    txvAlcoholPercentage.text = buildString {
                        append("%${beer.abv} alcohol")
                    }
                    txvTagline.text = buildString {
                        append("Tagline: ${beer.tagline}")
                    }
                    txvDescription.text = beer.description
                    txvIngredients.text = buildString {
                        append("MALT - ")
                        beer.ingredients.malt.forEach { malt ->
                            append("${malt.name} ${malt.amount.value} ${malt.amount.unit}, ")
                        }
                        append("\n\n")
                        append("HOPS - ")
                        beer.ingredients.hops.forEach { hops ->
                            append("${hops.name} ${hops.amount.value} ${hops.amount.unit}, ")
                        }
                    }
                    txvFoodPairing.text = buildString {
                        beer.foodPairing.forEach { foodPairing ->
                            append("$foodPairing, ")
                        }
                    }
                    txvBrewersTips.text = beer.brewersTips
                }
            }
        }
    }

    /**
     * handles visibility status when loading
     */
    private fun loadingStateVisibility(binding: FragmentSingleBeerBinding) {
        binding.apply {
            singleBeerProgressbar.visibility = View.VISIBLE
            txvErrorMessage.visibility = View.GONE
            imvBeer.visibility = View.GONE
            txvName.visibility = View.GONE
            txvAlcoholPercentage.visibility = View.GONE
            txvTagline.visibility = View.GONE
            txvDescriptionTitle.visibility = View.GONE
            txvDescription.visibility = View.GONE
            txvIngredientsTitle.visibility = View.GONE
            txvIngredients.visibility = View.GONE
            txvFoodPairingTitle.visibility = View.GONE
            txvFoodPairing.visibility = View.GONE
            txvBrewersTipsTitle.visibility = View.GONE
            txvBrewersTips.visibility = View.GONE
        }
    }

    /**
     * handles visibility status when there is a network error
     */
    private fun messageStateVisibility(binding: FragmentSingleBeerBinding) {
        binding.apply {
            singleBeerProgressbar.visibility = View.GONE
            txvErrorMessage.visibility = View.VISIBLE
            imvBeer.visibility = View.GONE
            txvName.visibility = View.GONE
            txvAlcoholPercentage.visibility = View.GONE
            txvTagline.visibility = View.GONE
            txvDescriptionTitle.visibility = View.GONE
            txvDescription.visibility = View.GONE
            txvIngredientsTitle.visibility = View.GONE
            txvIngredients.visibility = View.GONE
            txvFoodPairingTitle.visibility = View.GONE
            txvFoodPairing.visibility = View.GONE
            txvBrewersTipsTitle.visibility = View.GONE
            txvBrewersTips.visibility = View.GONE
        }
    }

    /**
     * handles visibility status when data has loaded successfully
     */
    private fun dataStateVisibility(binding: FragmentSingleBeerBinding) {
        binding.apply {
            singleBeerProgressbar.visibility = View.GONE
            txvErrorMessage.visibility = View.GONE
            imvBeer.visibility = View.VISIBLE
            txvName.visibility = View.VISIBLE
            txvAlcoholPercentage.visibility = View.VISIBLE
            txvTagline.visibility = View.VISIBLE
            txvDescriptionTitle.visibility = View.VISIBLE
            txvDescription.visibility = View.VISIBLE
            txvIngredientsTitle.visibility = View.VISIBLE
            txvIngredients.visibility = View.VISIBLE
            txvFoodPairingTitle.visibility = View.VISIBLE
            txvFoodPairing.visibility = View.VISIBLE
            txvBrewersTipsTitle.visibility = View.VISIBLE
            txvBrewersTips.visibility = View.VISIBLE
        }
    }

}