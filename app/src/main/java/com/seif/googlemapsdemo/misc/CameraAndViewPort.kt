package com.seif.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class CameraAndViewPort {
    val cairo: CameraPosition = CameraPosition.Builder()
        .target(LatLng(30.05114940018266, 31.235459175307987))
        .zoom(17f)
        .bearing(0f) // towards north
        .tilt(45f) // viewing angle of 45 degree
        .build()
}