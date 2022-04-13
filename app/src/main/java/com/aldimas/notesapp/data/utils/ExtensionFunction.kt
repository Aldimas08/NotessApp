package com.aldimas.notesapp.data.utils

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aldimas.notesapp.ui.MainActivity
import com.example.notesapp.R
import com.google.android.material.appbar.MaterialToolbar

fun MaterialToolbar.setActionBar(requireActivity: FragmentActivity) {
    val navController = findNavController()
    val appBarConfiguration = AppBarConfiguration(navController.graph)

    (requireActivity as MainActivity).setSupportActionBar(this)
    setupWithNavController(navController, appBarConfiguration)

    //mengubah back arrow menjadi icon costume
    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.addFragment -> setNavigationIcon(R.drawable.ic_left_arrow)
            R.id.detailFragment -> setNavigationIcon(R.drawable.ic_left_arrow)
            R.id.updateFragment -> setNavigationIcon(R.drawable.ic_left_arrow)
        }
    }
}