package com.josphatmwania.hoppyhour.feature_beer.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josphatmwania.hoppyhour.R
import com.josphatmwania.hoppyhour.databinding.ItemBeerBinding
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer

class BeerPagingAdapter(
    private val onCLick: (Beer) -> Unit
) : PagingDataAdapter<Beer, BeerPagingAdapter.BeerViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val viewHolder = BeerViewHolder(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), parent.context
        )
        val position = viewHolder.absoluteAdapterPosition
        viewHolder.itemView.setOnClickListener { getItem(position)?.let { beer -> onCLick(beer) } }
        return viewHolder
    }

    class BeerViewHolder(private val binding: ItemBeerBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer?) {
            binding.apply {
                Glide.with(context)
                    .load(beer?.imageUrl)
                    .into(imvBeer)
                txvName.text = beer?.name
                txvTagline.text = beer?.tagline
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem == newItem
            }
        }
    }
}