package com.seif.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewPort {
    val cairo: CameraPosition = CameraPosition.Builder()
        .target(LatLng(30.05114940018266, 31.235459175307987))
        .zoom(17f)
        .bearing(0f) // towards north
      //  .tilt(45f) // viewing angle of 45 degree
        .build()

    val newWork: CameraPosition = CameraPosition.Builder()
        .target(LatLng(40.7164203933524, -74.00440676650565))
        .zoom(17f)
        .bearing(0f) // towards north
        .tilt(45f) // viewing angle of 45 degree
        .build()

    val gizaBoundaries = LatLngBounds(
        LatLng(30.04036951573279, 30.95280653173993), // south west
        LatLng(30.056681415273314, 30.976195195646326) // north east
    )
}