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

    private lateinit var clusterManager: ClusterManager<MyItem>

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

    private val titleList = listOf(
     "title 1",
     "title 2",
     "title 3",
     "title 4",
     "title 5",
     "title 6",
     "title 7",
     "title 8",
     "title 9",
     "title 10"
    )

    private val snippetList = listOf(
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum",
        "lorem Ipsum"
    )


    private val cairo = LatLng(30.05114940018266, 31.235459175307987)

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

        clusterManager = ClusterManager(this, map)
        map.setOnCameraIdleListener(clusterManager) // the cluster manage will handle camera idle
        map.setOnMarkerClickListener(clusterManager) // the cluster manage will handle marker clicked
        addMarkers()


     //   checkLocationPermission()
        //Shapes().addPolygon(map)
//        val groundOverlay = overlays.addGroundOverlayWithTag(map)
//        lifecycleScope.launch {
//            delay(4000)
//            Log.d("maps", groundOverlay?.tag.toString())
//        }

//        val layer = GeoJsonLayer(map, R.raw.maps, this)
//        layer.addLayerToMap()
//
//        val polygonStyle = layer.defaultPolygonStyle
//        polygonStyle.apply {
//            fillColor = Color.BLUE // ContextCompat.getColor(this, R.color.teal_700)
//        }
//
//        layer.setOnFeatureClickListener {
//            Log.d("maps", "feature ${it.getProperty("country")}")
//        }
//
//        for (feature in layer.features){
//            if(feature.hasProperty("country"))
//                Log.d("maps", "success")
//        }

    }

    private fun addMarkers() {
        locationList.zip(titleList).zip(snippetList).forEach { pair ->
            val myItem = MyItem(pair.first.first, "Title: ${pair.first.second}","Snippet: ${pair.second}")
            clusterManager.addItem(myItem)
        }
    }

    private fun checkLocationPermission(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
            Toast.makeText(this, "location already granted", Toast.LENGTH_SHORT).show()
        }
        else {
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
        if (requestCode!=1){
            return
        }
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show()
            map.isMyLocationEnabled = true
        }
        else{
            Toast.makeText(this, "we need your permission!", Toast.LENGTH_SHORT).show()
        }

    }
}


