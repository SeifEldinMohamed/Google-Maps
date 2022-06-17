package com.seif.googlemapsdemo.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import com.seif.googlemapsdemo.R
import java.lang.Exception

class TypesAndStyles {

    fun setMapStyle(googleMap: GoogleMap, context:Context){
        try {
            val success = googleMap.setMapStyle( // will return true if this style successfully applied to our map
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.style
                )
            )
            if(!success){
                Log.d("main", "failed to load style")
            }
        } catch (e: Exception){
            Log.d("main", e.toString())
        }
    }

    fun setMapType(item: MenuItem, map: GoogleMap){
        when(item.itemId){
            R.id.normal_map -> map.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid_map -> map.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite_map -> map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain_map -> map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            R.id.none_map -> map.mapType = GoogleMap.MAP_TYPE_NONE
        }
    }
}