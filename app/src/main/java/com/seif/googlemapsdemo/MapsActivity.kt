package com.seif.googlemapsdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.shapes.Shape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BitmapCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.seif.googlemapsdemo.databinding.ActivityMapsBinding
import com.seif.googlemapsdemo.misc.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typesAndStyles by lazy { TypesAndStyles() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }
    private val overlays by lazy { Overlays() }
    private val cairo = LatLng(30.05114940018266, 31.235459175307987)

    private val locationList = listOf(
        LatLng(30.05114940018266, 31.235459175307987),
        LatLng(30.070166636477293, 31.291077460547545),
        LatLng(30.019346801023712, 31.29794391551539),
        LatLng(30.033020197125637, 31.16473468913916),
        LatLng(30.077891345086947, 31.17915424457164),
        LatLng(30.077891345086947, 31.17915424457164),
        LatLng(30.077891345086947, 31.17915424457164),
        LatLng(30.077891345086947, 31.17915424457164),
        LatLng(30.077891345086947, 31.17915424457164),
        LatLng(30.077891345086947, 31.17915424457164)
    )

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

    // this function will be called when the activity starts and from getMapAsync when google map is ready
    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val cairoMarker =
            map.addMarker(
                MarkerOptions()
                    .position(cairo)
                    .title("Marker in Cairo")
                    .snippet("hello from cairo!")
            )

        cairoMarker?.tag = "Restaurant"
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cairo, 10f))
        //  map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.cairo))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }

        typesAndStyles.setMapStyle(map, this)
        addHeatMap()
        // checkLocationPermission()
    }

    private fun addHeatMap(){
        val colors = intArrayOf(
            Color.rgb(0,128, 255), // blue -> low
            Color.rgb(204,0,204), // pink -> medium
            Color.rgb(255,255,51) // yellow -> high
        )
        val startPoints = floatArrayOf(0.2f, 0.5f, 0.8f) // define our gradient color
        val gradient = Gradient(colors, startPoints)

        val provider = HeatmapTileProvider.Builder()
            .radius(50)
            .data(locationList)
            .opacity(0.3) // default value = 0.7
            .gradient(gradient)
            .build()
        val overlay = map.addTileOverlay(TileOverlayOptions().tileProvider(provider))

        lifecycleScope.launch {
            delay(5000)
            overlay?.clearTileCache() // so we can se the changes
            provider.setRadius(20)
            // provider.setData()
           //  overlay.remove() // to remove our heatmap
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
            Toast.makeText(this, "location already granted", Toast.LENGTH_SHORT).show()
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult( // will be triggered each time the permission is granted or denied
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != 1) {
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show()
            map.isMyLocationEnabled = true
        } else {
            Toast.makeText(this, "we need your permission!", Toast.LENGTH_SHORT).show()
        }
    }

}


