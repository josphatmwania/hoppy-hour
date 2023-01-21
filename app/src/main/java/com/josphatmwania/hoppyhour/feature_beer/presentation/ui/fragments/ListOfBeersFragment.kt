package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.josphatmwania.hoppyhour.databinding.FragmentListOfBeersBinding

class ListOfBeersFragment : Fragment() {
    private lateinit var binding: FragmentListOfBeersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfBeersBinding.inflate(inflater, container, false)
        return binding.root
    }
}