package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josphatmwania.hoppyhour.common.navigate
import com.josphatmwania.hoppyhour.databinding.FragmentListOfBeersBinding
import com.josphatmwania.hoppyhour.feature_beer.presentation.adapter.BeerLoadStateAdapter
import com.josphatmwania.hoppyhour.feature_beer.presentation.adapter.BeerPagingAdapter
import com.josphatmwania.hoppyhour.feature_beer.presentation.ui.viewmodels.ListOfBeersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfBeersFragment : Fragment() {
    private lateinit var binding: FragmentListOfBeersBinding
    private lateinit var beerAdapter: BeerPagingAdapter
    private val viewModel: ListOfBeersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        beerAdapter = BeerPagingAdapter {
            val action =
                ListOfBeersFragmentDirections.actionListOfBeersFragmentToSingleBeerFragment(it.id)
            navigate(action)
        }
        initAdapterData()
        binding.recyclerView.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun initAdapterData() {
        beerAdapter.withLoadStateHeaderAndFooter(
            header = BeerLoadStateAdapter(beerAdapter::retry),
            footer = BeerLoadStateAdapter(beerAdapter::retry)
        )
        lifecycleScope.launchWhenCreated {
            viewModel.allBeers.collect { beerPagingData ->
                beerPagingData?.let {
                    beerAdapter.submitData(it)
                }
            }
        }
    }
}