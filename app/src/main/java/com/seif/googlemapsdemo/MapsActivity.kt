package com.seif.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.seif.googlemapsdemo.databinding.ActivityMapsBinding
import com.seif.googlemapsdemo.misc.CameraAndViewPort
import com.seif.googlemapsdemo.misc.TypesAndStyles


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typesAndStyles by lazy { TypesAndStyles() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }

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
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typesAndStyles.setMapType(item, map)
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
      //  val newYork = LatLng(40.7164203933524, -74.00440676650565)
        val cairoMarker =
            map.addMarker(MarkerOptions().position(cairo).title("Marker in Cairo").draggable(true))
        cairoMarker?.tag = "Restaurant"
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cairo, 10f))
        //  map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.cairo))

        map.uiSettings.apply {
            isZoomControlsEnabled = true
        }
        typesAndStyles.setMapStyle(map, this)
        map.setOnMarkerDragListener(this)

//        lifecycleScope.launch {
//            delay(4000)
//            //  map.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewPort.gizaBoundaries.center, 10f),2000, null)
//            //map.addMarker(MarkerOptions().position(newYork).title("Marker in newYork"))
//            // map.setLatLngBoundsForCameraTarget(cameraAndViewPort.gizaBoundaries)
//            cairoMarker?.remove()
//
//        }

    }

    override fun onMarkerDragStart(marker: Marker) { // called when the user start drag the marker(long press)
        Log.d("drag", "start")
    }

    override fun onMarkerDrag(marker: Marker) { // called when the user drag the marker all over the map
        Log.d("drag", "drag")

    }

    override fun onMarkerDragEnd(marker: Marker) { // called when the user put the marker in a place on map (release his finger)
        Log.d("drag", "end")
    }

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this, "location: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            map.addMarker(MarkerOptions().position(it).title("new Marker"))
        }
    }


}

