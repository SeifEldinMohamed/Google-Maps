package com.seif.googlemapsdemo.misc

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.seif.googlemapsdemo.R
import com.seif.googlemapsdemo.databinding.CustomInfoWindowBinding

class CustomInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
  //  private val contentView: View = (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    private val binding = CustomInfoWindowBinding.inflate((context as Activity).layoutInflater)

    override fun getInfoContents(marker: Marker): View? { // will be called at first but if it returns null then the second function will be called
        renderViews(marker)
        return binding.root
    }

    override fun getInfoWindow(marker: Marker): View? { // if it's also returns null then the default info window will be used
        renderViews(marker)
        return binding.root
    }

    private fun renderViews(marker: Marker) {
        binding.txtTitle.text = marker.title.toString()
        binding.txtDescription.text = marker.snippet.toString()

    }
}