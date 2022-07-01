package com.seif.googlemapsdemo.misc

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.seif.googlemapsdemo.R

class Overlays {
    private val cairo = LatLng(30.05114940018266, 31.235459175307987)
    private val cairoBounds = LatLngBounds(
        LatLng(30.02380572436222, 31.191513863513762), // southEast
        LatLng(30.065709799502486, 31.25193866723082), // NorthWest
    )

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {
        return map.addGroundOverlay(
            GroundOverlayOptions().apply {
                // position(cairo, 10000f, 10000f) // LatLng object , 1 float(represent width), 2 float(represent width and height) in meters
                positionFromBounds(cairoBounds) // the ground overlay is placed in the center of the two bounds
                image(BitmapDescriptorFactory.fromResource(R.drawable.flower))
            }
        )
    }

    fun addGroundOverlayWithTag(map: GoogleMap): GroundOverlay? {
        val overlayGround = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                // position(cairo, 10000f, 10000f) // LatLng object , 1 float(represent width), 2 float(represent width and height) in meters
                positionFromBounds(cairoBounds) // the ground overlay is placed in the center of the two bounds
                image(BitmapDescriptorFactory.fromResource(R.drawable.flower))
            }
        )
        overlayGround?.tag = "Hello my tag"
        return overlayGround
    }

}