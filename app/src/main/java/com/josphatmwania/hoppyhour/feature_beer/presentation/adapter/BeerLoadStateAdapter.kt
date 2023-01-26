package com.josphatmwania.hoppyhour.feature_beer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.josphatmwania.hoppyhour.databinding.ItemLoadStateBinding

class BeerLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<BeerLoadStateAdapter.BeerLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: BeerLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BeerLoadStateViewHolder {
        val binding =
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerLoadStateViewHolder(binding, retry)
    }

    class BeerLoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.btnRetry.setOnClickListener { retry() }
            if (loadState is LoadState.Error) {
                binding.txvErrorMessage.text = loadState.error.localizedMessage
            }
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                txvErrorMessage.isVisible = loadState is LoadState.Error
                btnRetry.isVisible = loadState is LoadState.Error
            }
        }

    }

}