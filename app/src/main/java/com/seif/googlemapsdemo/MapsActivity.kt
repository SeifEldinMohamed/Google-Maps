package com.seif.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.seif.googlemapsdemo.databinding.ActivityMapsBinding
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this) // Sets a callback object which will be triggered when the GoogleMap instance is ready to be used.
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.normal_map -> map.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.hybrid_map -> map.mapType = GoogleMap.MAP_TYPE_HYBRID
            R.id.satellite_map -> map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            R.id.terrain_map -> map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            R.id.none_map -> map.mapType = GoogleMap.MAP_TYPE_NONE
        }
        return true
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
// this function will be called when the activity starts and from getMapAsync when google map is ready
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val cairo = LatLng(30.05114940018266, 31.235459175307987)
        map.addMarker(MarkerOptions().position(cairo).title("Marker in Cairo"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cairo, 10f))

        map.uiSettings.apply {
            isZoomGesturesEnabled = true // used for enabling or disabling gesture controls(double tab or by using 2 fingers) for zooming (enabled by default)
            isZoomControlsEnabled = true// used for enabling or disabling zoom buttons (disabled by default)
            isScrollGesturesEnabled = true // If enabled, users can use cameraView on our map  (enabled by default)
            isTiltGesturesEnabled = true // If enabled, users can use a two-finger vertical down swipe to tilt the camera. (enabled by default)`
            isRotateGesturesEnabled = true //  If enabled, users can use a two-finger rotate gesture to rotate the camera. (enabled by default)`
            isMyLocationButtonEnabled = true // If the button is enabled, The my-location button causes the camera to move such that the user's location is in the center of the map. it is only shown when the my-location layer is enabled. (enabled by default)
            isMapToolbarEnabled = true // If enabled, and the Map Toolbar can be shown in the current context, users will see a bar with various context-dependent actions, including 'open this map in the Google Maps app' and 'find directions to the highlighted marker in the Google Maps app' (enabled by default)
            isCompassEnabled = true //The compass is an icon on the map that indicates the direction of north on the map. If enabled, it is only shown when the camera is tilted or rotated away from its default orientation (tilt of 0 and a bearing of 0). (0enabled by default)

        }
        map.setPadding(0,0,300,0)
        setMapStyle(map)

    }

    private fun setMapStyle(googleMap: GoogleMap){
        try {
            val success = googleMap.setMapStyle( // will return true if this style successfully applied to our map
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.style
                )
            )
            if(!success){
                Log.d("main", "failed to load style")
            }
        } catch (e:Exception){
            Log.d("main", e.toString())
        }
    }
}

// latitude: the point's latitude. This will be clamped to between -90 degrees and +90 degrees inclusive
// longitude: the point's longitude. This will be normalized to within -180 degrees and +180 degrees inclusive

// debug
// SHA1: 8D:FD:C0:B2:C3:41:10:C0:42:1E:60:F6:0B:0E:27:14:3C:F9:BC:59
// release
//  SHA1: 68:56:09:D6:DB:77:F2:E2:67:28:B4:E2:41:14:DB:53:33:4F:54:33


// video: 5 (controls and gestures)
// we can modify this controls and gestures using ui setting class which can be obtained from google maps class
// by default we can use all gestures in our google maps
// there are about 20 zoom levels form 1..20 if disabled then the zoom control are not shown on the screen
// to control zoom levels we can use CameraUpdateFactory.newLatLngZoom(cairo, 15f)

// video: 6 (map padding)
// with map padding we can specify how many pixels we want to move those ui controllers away from fragment map container
// the center point will move to thw left side when use map.setPadding(0,0,300,0)
// we can't move google logo image from app

// video: 7 (change map type)
// google maps API includes many  maps types as
// normal: used for navigating with car (normal look)
// hybrid: used mix between satellite and normal types ( street names + satellite view)
// satellite: satellite view
// terrain ( تضاريس ): used when go hiking to see the terrain of this place
// none: no maps show

// video: 8 (change map style):
// go to https://mapstyle.withgoogle.com/ then choose your style and get it's json
// then put this json in raw and use it in googleMap.setMapStyle( // will return true if this style successfully applied to our map
//                MapStyleOptions.loadRawResourceStyle(
//                    this,
//                    R.raw.style
//                )
// to load this style.