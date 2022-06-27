package com.seif.googlemapsdemo.misc

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.seif.googlemapsdemo.R
import kotlinx.coroutines.delay

class Shapes {
    private val cairo = LatLng(30.05114940018266, 31.235459175307987)
    private val giza = LatLng(30.04036951573279, 30.95280653173993)
    private val newYork = LatLng(40.7164203933524, -74.00440676650565)

    private val p0 = LatLng(30.070166636477293, 31.291077460547545)
    private val p1 = LatLng(30.019346801023712, 31.29794391551539)
    private val p2 = LatLng(30.033020197125637, 31.16473468913916)
    private val p3 = LatLng(30.077891345086947, 31.17915424457164)

    private val p00 = LatLng(30.058482456352106, 31.251989673816073)
    private val p01 = LatLng(30.039471496737892, 31.2469912405689)
    private val p02 = LatLng(30.043405098104202, 31.212911013883616)
    private val p03 = LatLng(30.059006844893435, 31.21533449667013)

    private suspend fun addPolyline(map: GoogleMap) {
        val polyLine: Polyline = map.addPolyline(
            PolylineOptions().apply {
                add(cairo, giza, newYork) // takes one or more latLng object
                width(5f)
                color(Color.WHITE)
                geodesic(true) // Specifies whether to draw each segment of this polyline as a geodesic. The default setting is false ( draw an arc instead of straight line)
                clickable(true)
            }
        )
        delay(6000)

        polyLine.points = listOf(
            giza, cairo, newYork
        ) // change the actual polyline
    }

    fun addPolygon(map: GoogleMap) {
        map.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.black) // color of inner area
                strokeColor(R.color.black) // color of line
                addHole(listOf(p00, p01, p02, p03)) // all points must be inside the larger polygon
            }
        )
    }

    fun addCircle(map: GoogleMap) {
        val circle = map.addCircle(
            CircleOptions().apply {
                center(cairo)
                radius(50000.0)
                fillColor(R.color.teal_200)
                strokeColor(R.color.teal_200)
            }
        )
    }
}