package com.seif.googlemapsdemo.summary


// latitude: the point's latitude. This will be clamped to between -90 degrees and +90 degrees inclusive
// longitude: the point's longitude. This will be normalized to within -180 degrees and +180 degrees inclusive

// debug: generate debug certificate finger print from gradle -> Tasks -> Android -> double click on signing report
// SHA1: 8D:FD:C0:B2:C3:41:10:C0:42:1E:60:F6:0B:0E:27:14:3C:F9:BC:59


// release: first generate signed bundle or apk then add this command in terminal "keytool -list -v -keystore your_keystore_name -alias your_alias_name"
// your_keystore_name: your full path where our keystore where is located for example (C:\Users\Stefan\Desktop\googlemaps.jks
// your_alias_name": alias name for example (key0)
// then we will be asked to enter keystore password
// SHA1: 68:56:09:D6:DB:77:F2:E2:67:28:B4:E2:41:14:DB:53:33:4F:54:33


/** video: 5 (controls and gestures) **/
// we can modify this controls and gestures using ui setting class which can be obtained from google maps class
// by default we can use all gestures in our google maps
// there are about 20 zoom levels form 1..20 if disabled then the zoom control are not shown on the screen
// to control zoom levels we can use CameraUpdateFactory.newLatLngZoom(cairo, 15f)

//            isZoomGesturesEnabled = true // used for enabling or disabling gesture controls(double tab or by using 2 fingers) for zooming (enabled by default)
//            isZoomControlsEnabled = true// used for enabling or disabling zoom buttons (disabled by default)
//            isScrollGesturesEnabled = true // If enabled, users can use cameraView on our map  (enabled by default)
//            isTiltGesturesEnabled = true // If enabled, users can use a two-finger vertical down swipe to tilt the camera. (enabled by default)`
//            isRotateGesturesEnabled = true //  If enabled, users can use a two-finger rotate gesture to rotate the camera. (enabled by default)`
//            isMyLocationButtonEnabled = true // If the button is enabled, The my-location button causes the camera to move such that the user's location is in the center of the map. it is only shown when the my-location layer is enabled. (enabled by default)
//            isMapToolbarEnabled = true // If enabled, and the Map Toolbar can be shown in the current context, users will see a bar with various context-dependent actions, including 'open this map in the Google Maps app' and 'find directions to the highlighted marker in the Google Maps app' (enabled by default)
//            isCompassEnabled = true //The compass is an icon on the map that indicates the direction of north on the map. If enabled, it is only shown when the camera is tilted or rotated away from its default orientation (tilt of 0 and a bearing of 0). (0enabled by default)


/** video: 6 (map padding) **/
// with map padding we can specify how many pixels we want to move those ui controllers away from fragment map container
// the center point will move to thw left side when use -> map.setPadding(0,0,300,0)
// we can't move google logo image from app


/** video: 7 (change map type) **/
// google maps API includes many  maps types as
// normal: used for navigating with car (normal look)
// hybrid: used mix between satellite and normal types ( street names + satellite view)
// satellite: satellite view
// terrain ( تضاريس ): used when go hiking to see the terrain of this place
// none: no maps show

/** video: 8 (change map style): **/
// go to https://mapstyle.withgoogle.com/ then choose your style and get it's json
// then put this json in raw and use it in googleMap.setMapStyle( // will return true if this style successfully applied to our map
//                MapStyleOptions.loadRawResourceStyle(
//                    this,
//                    R.raw.style
//                )
// to load this style.

/** video: 9 (about camera position): **/
//google maps sdk allows use to change user's view port of the map by modifying camera position
// so changes of the camera will not make any changes to markers, overlays and other graphics we can use
// the map view is modeled as a camera looking down to a flat surface (90 degree angle)
// the position of the camera is specified by multiple properties:
// 1) Target = location
// 2) Bearing = orientation
// 3) Tilt = viewing Angle
// 4) Zoom: Determines Scale of the Map
// use getMinZoomLevel() function to check for minimum zoom level on a specific device as it may varies over devices

/** video: 10 (show building in 3d on map) **/
//     val cairo: CameraPosition = CameraPosition.Builder()
//        .target(LatLng(30.05114940018266, 31.235459175307987))
//        .zoom(17f)
//        .bearing(0f) // towards north
//        .tilt(45f) // viewing angle of 45 degree
//        .build()

/** video: 11 (Change Zoom Level and set MaxMin zoom level) **/
// set min zoom level -> map.setMinZoomPreference(15f)
// set max zoom level -> map.setMaxZoomPreference(15f)
// CameraUpdateFactory.zoomIn() // zoom in by only one level
// CameraUpdateFactory.zoomOut() // zoom out by only one level
// CameraUpdateFactory.zoomTo(12) // zoom to a specific level (to level 12)
// CameraUpdateFactory.zoomBy(3f) // zoom by a specific amount of zoom levels ( 12+3=15)

/** video: 12 (update camera position programmatically) **/
// and scrolling the postiion of the camera by shifting the center of the view with the specified number of pixels whether on x or y axis
// scrollBy() method: let us change camera position with a specific number of pixels on x and y axis
// +ve in x-axis -> go right (scroll left)
// -ve in x-axis -> go left (scroll right)
// +ve in y-axis -> go down (scroll down)
// -ve in y-axis -> go up (scroll up)

/** video: 13 (set boundaries on the map) **/
// newLatLngBound() function: which lets us specifies the location boundaries we want to display on the map
// ( we can only call this function when map layout completed bec apis calculates the display boundaries of the map during layout )
//  map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.gizaBoundaries, 0))
//  map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewPort.gizaBoundaries.center, 10f)) (with in the boundaries)

/** video: 14 (restrict user from scrolling) **/
// the user will be able to move through this boundaries only
// map.setLatLngBoundsForCameraTarget(cameraAndViewPort.gizaBoundaries)

/** video: 15 (animate camera movement) **/
//  map.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewPort.gizaBoundaries.center, 10f),2000, null)
//  map.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
//  map.animateCamera(CameraUpdateFactory.scrollBy(1500f,0f), 2000, null)
// with callback:
//         map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.cairo), 2000, object: GoogleMap.CancelableCallback{
//                override fun onFinish() { // will be called when our animation is complete
//                    Toast.makeText(this@MapsActivity, "finished", Toast.LENGTH_SHORT).show()
//                }
//                override fun onCancel() { // will be called when our animation is cancelled
//                    Toast.makeText(this@MapsActivity, "canceled", Toast.LENGTH_SHORT).show()
//
//                }
//            })

/** video: 16 (single and long click listener): **/
// can be helpful when we use with those markers (ex: add new marker when user long Clicked )
// map.setOnMapClickListener {} for single click
//  map.setOnMapLongClickListener {} for long click

// video: 17 (Markers):
// markers used to identify the location on the map and default marker used standard icon
// we can customize marker properties like (color, image, opacity, anchor point, rotation, z-index, tag ...)
// markers are interactive and they receive click events by default (single click open info window)

/** video: 18 (store data object in a marker): **/
// save data object in marker using setTag function and retrieve same dat object
// used to distinguish between diff types of markers
//
// implement GoogleMap.OnMarkerClickListener
// in onMapReady function ->  map.setOnMarkerClickListener(this)
//
//     override fun onMarkerClick(marker: Marker): Boolean {
//        marker.let {
//            Log.d("main", it.tag.toString())
//        }
//    //    return true // the title of the marker will not appear in info window + tag
//        return false  // the title of the marker will appear in info window + tag
//    }

/** video: 19 (enable marker dragging): "Disabled by default" **/
// On Long Press we can move (drag) the marker any where on thw map
// val cairoMarker =  map.addMarker(MarkerOptions().position(cairo).title("Marker in Cairo").draggable(true))
//
// implement drag event: by implementing GoogleMap.OnMarkerDragListener
//  in onMapReady ->  map.setOnMarkerDragListener(this)
//     override fun onMarkerDragStart(marker: Marker) { // called when the user start drag the marker(long press)
//        Log.d("drag", "start")
//    }
//
//    override fun onMarkerDrag(marker: Marker) { // called when the user drag the marker all over the map
//        Log.d("drag", "drag")
//
//    }
//
//    override fun onMarkerDragEnd(marker: Marker) { // called when the user put the marker in a place on map (release his finger)
//        Log.d("drag", "end")
//    }

/** video: 20 (marker customization): **/
// 1- change color of marker:
//   map.addMarker(MarkerOptions()
//                .position(cairo)
//                .title("Marker in Cairo")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
// we can use custom HUE color(0..360) by using HSL calculator (H: hue, s: saturation, l: lightness)
// we will adjust hue value only (ex: 134f)
// if we want add a custom marker from resource (drawable) then we have to convert it to bitmap
// Vector:
// .icon(fromVectorToBitmap(R.drawable.ic_android, Color.parseColor("#00D176")))
//
//     private fun fromVectorToBitmap(id:Int, color:Int):BitmapDescriptor{
//        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
//        if(vectorDrawable!=null){
//            val bitmap = Bitmap.createBitmap(
//                vectorDrawable.intrinsicWidth, // width and height of our vector drawable to be added to our bitmap object
//                vectorDrawable.intrinsicHeight,
//                Bitmap.Config.ARGB_8888
//            )
//            val canvas = Canvas(bitmap)
//            vectorDrawable.setBounds(0,0,canvas.width, canvas.height)
//            DrawableCompat.setTint(vectorDrawable, color) // to change our vector color
//            vectorDrawable.draw(canvas)
//            return BitmapDescriptorFactory.fromBitmap(bitmap)
//        }
//        else{ // null
//            Log.d("map", "Resource not found!")
//            return BitmapDescriptorFactory.defaultMarker()
//        }
//    }
//
// Image(png) -> .icon(BitmapDescriptorFactory.fromResource(R.drawable.customMarker))
//
// .alpha(0.5f) // visibility of the marker (1 -> visible, 0 -> not visible)
// .rotation(90f) // rotation of the marker
// .flat(true) // Sets whether this marker should be flat against the map true or a billboard facing the camera false. If the marker is flat against the map, it will remain stuck to the map as the camera rotates and tilts but will still remain the same size as the camera zooms, unlike a GroundOverlay. If the marker is a billboard, it will always be drawn facing the camera and will rotate and tilt with the camera. The default value is false.

/** video: 21 (marker zIndex):(default zIndex value = 0) **/
// zIndex: it specifies the stack order of the marker relative to other markers on the map
// a marker with higher zIndex will be drawn on the top of other markers with lower zIndex
// note: markers always drawn above other layers and overlays (ground, polygons and other shapes)
// so markers are considered to be a separate zIndex group compared to other overlays
//   .zIndex(1f)) // now it will be on top the first marker when there is an overlay

/** video: 21 (info window): **/
// allows you to display information to the user when they tab on the marker
// if we have one info window open on a marker and we tried to open another info window of another marker the first one will close automatically
// to add description to info window: .snippet("hello from cairo!")
// to make info window not appear we have to make onMarkerClick function return true overwritten the default behaviour of the marker

/** video: 22 (Custom info window): **/
// 1- design custom layout
// 2- implement CustomInfoWindowAdapter
// 3- map.setInfoWindowAdapter(CustomInfoAdapter(this))

/** video: 23 (introduction to polyline): **/
// polyline: Is a set of latLng location which forms a line segment that connect those same locations in an order sequence
//
//   private fun addPolyline(){
//       val polyLine: Polyline =  map.addPolyline(
//            PolylineOptions().apply {
//                add(cairo, giza, newYork) // takes one or more latLng object
//                width(5f)
//                color(Color.WHITE)
//                geodesic(true) // Specifies whether to draw each segment of this polyline as a geodesic. The default setting is false ( draw an arc instead of straight line)
//                clickable(true) // false by default
//            }
//        )
//        val newList = listOf(
//            giza, cairo, newYork
//        )
//        polyLine.points = newList // change the actual polyline
//    }
// click listener on polyline:
// 1- implement GoogleMap.OnPolylineClickListener
// 2- map.setOnPolylineClickListener(this)
// 3- don't forget to make polyline clickable
// 4- override onPolylineClick() function
//
//note: (when coming to clickable events) markers then other shapes based on their zIndex value

/** video: 24 (introduction to polygon): **/
// polygon is a similar to polyline in a way that both of them consists of coordinates in ordered sequence
// this polygon is autocompleted our shape so if the first coordinates is different from last coordinate then
// this api will automatically close polygon shape by appending the first coordinate at the end of the sequence of our coordinates
// holo polygon: a multiple polygons can be used together to create a complex shapes as (donuts)
// the trick is to define two polygons in the same area where the larger polygon will act as a fill area while the inner(smaller) polygon will be fully transparent
//
//  val polygon = map.addPolygon(
//            PolygonOptions().apply {
//                add(p0, p1, p2, p3)
//                fillColor(R.color.black) // color of inner area
//                strokeColor(R.color.black) // color of line
//                addHole(listOf(p00, p01, p02, p03)) // all points must be inside the larger polygon
//                zIndex(1f) // increase it's priority
//            }
//        )

/** video: 24 (introduction to circle): **/
// to draw a circular shape on the map we need to define 2 main properties:
// 1- location point: which we will use as a center point of our circle
// 2- radius: which represented in meter on the map
// our circle will appear on the map almost as a perfect circle when it's located near the equator
// and it will appear in a non circular shape when it moves away from equator
//
//     val circle = map.addCircle(
//            CircleOptions().apply {
//                center(cairo)
//                radius(50000.0)
//                fillColor(R.color.teal_200)
//                strokeColor(R.color.teal_200)
//            }
//        )
// note: all shapes are not clickable by default

/** video: 25 (shape customization): **/
// stroke pattern can be used on polyLines, polygons and circles and the default stroke pattern is a solid line for a polyLines and out lines of polygons and circles
// specifies as dots, gaps and dash we can specifies all of those 3 or 2 of them
//
//  val pattern = listOf(Dot(), Gap(30f), Dash(50f)) // start with dot then have a 30 pixels gap then a dash
// for polyLines:  pattern(pattern)
// for polygons and circles: strokePattern(pattern)
//
// joinTypes: for polyLines and outlines of a polygon we can specify a bevel (straight line) or round joinType
// and the join type affect the internal bands of the line التحويلة
// they don't affect dots bec they are always circular
//
//  jointType(JointType.ROUND)
//  jointType(JointType.BEVEL)
//
// linesCaps:  we can specify a cap style on each end of the line, we have 2 properties:
// 1- startCap:
// 2- endCap:
// he have many options to specify to my caps:

// buttCap: which is a default line cap
// squareCap:
// roundCap:
//
//  startCap(RoundCap())
//  endCap(ButtCap())
//  endCap(SquareCap())
//
// also there is a way to use custom bitmaps to my caps:
// startCap(CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.ic_android), 20f)) // stroke width in pixels(when we increase this map it will shrink)

/** video: 26 (ground overlays): **/
// are image overlays that are tied to latitude and longitude coordinates, and it fixed to a map, they are oriented against the earth surface, so rotating, tilting, or zooming the map will change the orientation of the image
// are useful when we want to fix single image at one area on the map, the image size increased when we zoom, if we rotate it still be fixed
// specify 2 main properties to the overlay object: 1- position  2- image
//
//  private val cairo = LatLng(30.05114940018266, 31.235459175307987)
//  private val cairoBounds = LatLngBounds(
//        LatLng(30.02380572436222, 31.191513863513762), // southEast
//        LatLng(30.065709799502486, 31.25193866723082), // NorthWest
//    )
//    fun addGroundOverlay(map: GoogleMap){
//        val groundOverlay = map.addGroundOverlay(
//            GroundOverlayOptions().apply {
//                position(cairo, 10000f, 10000f) // LatLng object , 1 float(represent width), 2 float(represent width and height) in meters
//                positionFromBounds(cairoBounds) // the ground overlay is placed in the center of the two bounds
//                image(BitmapDescriptorFactory.fromResource(R.drawable.flower))
//            }
//        )
//    }
//
// groundOverlay?.remove()
// groundOverlay?.transparency = 0.5f
// groundOverlay?.tag() // this tag information cam be used for example: add some zIndex Priorities so we can differentiate one overlay from another

/** video: 27 (My location layer): "part of maps sdk" **/
// my location layer : it's a way to locate yourself on the map just by pressing my location button on the google maps (located on top right corner)
// if we use my location layer we will not by able to receive the actual data of the current user position, instead our camera will move automatically to our location ( Easiest anf fastest way to locate our location on map )
// if we will use location of user then we need to request a location permission first (there are 2 main location permission):

// 1- ACCESS_COARSE_LOCATION permission: allows us to use wifi and mobile cellar data or both to determine the device's location
// the result will return a location in approximately to a city block
// 2- ACCESS_FINE_LOCATION permission: allows us to use wifi and mobile cellar data to determine the device's location as accurate as possible from the available location providers
// including the Global Positioning System (GPS)

// so after we choose which one of these permission we will use then we need to request a runtime permission (start from android 6.0 marshmello)


// Types of location access
// Each permission has a combination of the following characteristics:
// Category: Either foreground location or background location.
// Accuracy: Either precise location or approximate location.

// Foreground location :
//If your app contains a feature that shares or receives location information only once, or for a defined amount of time, then that feature requires foreground location access. Some examples include the following:
//
// Within a navigation app, a feature allows users to get turn-by-turn directions.
// Within a messaging app, a feature allows users to share their current location with another user.
// The system considers your app to be using foreground location if a feature of your app accesses the device's current location in one of the following situations:
// Additionally, it's recommended that you declare a foreground service type of location:
/**
* <!-- Recommended for Android 9 (API level 28) and lower. -->
* <!-- Required for Android 10 (API level 29) and higher. -->
* <service
*     android:name="MyNavigationService"
*     android:foregroundServiceType="location" ... >
*     <!-- Any inner elements would go here. -->
* </service>
**/
// You declare a need for foreground location when your app requests either the ACCESS_COARSE_LOCATION permission or the ACCESS_FINE_LOCATION :
// <manifest ... >
/**
*  <!-- Always include this permission -->
*  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
*
*  <!-- Include only if your app benefits from precise location access. -->
*  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
* </manifest>
**/

// Background location:
// An app requires background location access if a feature within the app constantly shares location with other users or uses the Geofencing API. Several examples include the following:
//
// Within a family location sharing app, a feature allows users to continuously share location with family members.
// Within an IoT app, a feature allows users to configure their home devices such that they turn off when the user leaves their home and turn back on when the user returns home.
//
// On Android 10 (API level 29) and higher, you must declare the ACCESS_BACKGROUND_LOCATION permission in your app's manifest in order to request background location access at runtime.
// On earlier versions of Android, when your app receives foreground location access, it automatically receives background location access as well.
/**
* <manifest ... >
*  <!-- Required only when requesting background location access on
*       Android 10 (API level 29) and higher. -->
*  <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
* </manifest>
**/

/** Note: The Google Play Store has a location policy concerning device location,
 * restricting background location access to apps that need it for their core functionality and meet related policy requirements.**/

// Approximate:
// Provides a device location estimate. If this location estimate is from the LocationManagerService
// or FusedLocationProvider, this estimate is accurate to within about 3 square kilometers (about 1.2 square miles).
// Your app can receive locations at this level of accuracy when you declare the ACCESS_COARSE_LOCATION permission
// but not the ACCESS_FINE_LOCATION permission.

// Precise:
// Provides a device location estimate that is as accurate as possible.
// If the location estimate is from LocationManagerService or FusedLocationProvider,
// this estimate is usually within about 50 meters (160 feet) and is sometimes
// as accurate as within a few meters (10 feet) or better. Your app can receive locations
// at this level of accuracy when you declare the ACCESS_FINE_LOCATION permission.

/** video: 58 (geoJson): "Extension of json data format" Utility library **/
// it represents geographical data
// GEOJson.io website: on this website we can draw polyLines, polygons, shapes and marker on the map then we can download this geoJson and import in our project
// we put this geoJson file in raw
//
//     val layer = GeoJsonLayer(map, R.raw.maps, this)
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