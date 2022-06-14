package com.seif.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.seif.googlemapsdemo.databinding.ActivityMapsBinding

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
        map.moveCamera(CameraUpdateFactory.newLatLng(cairo))


    }
}

// latitude: the point's latitude. This will be clamped to between -90 degrees and +90 degrees inclusive
// longitude: the point's longitude. This will be normalized to within -180 degrees and +180     degrees inclusive

// debug
// SHA1: 8D:FD:C0:B2:C3:41:10:C0:42:1E:60:F6:0B:0E:27:14:3C:F9:BC:59

// release
//  SHA1: 68:56:09:D6:DB:77:F2:E2:67:28:B4:E2:41:14:DB:53:33:4F:54:33