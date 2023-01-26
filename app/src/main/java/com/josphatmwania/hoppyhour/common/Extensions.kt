package com.josphatmwania.hoppyhour.common

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}