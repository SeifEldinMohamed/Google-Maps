package com.seif.googlemapsdemo.summary


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

//            isZoomGesturesEnabled = true // used for enabling or disabling gesture controls(double tab or by using 2 fingers) for zooming (enabled by default)
//            isZoomControlsEnabled = true// used for enabling or disabling zoom buttons (disabled by default)
//            isScrollGesturesEnabled = true // If enabled, users can use cameraView on our map  (enabled by default)
//            isTiltGesturesEnabled = true // If enabled, users can use a two-finger vertical down swipe to tilt the camera. (enabled by default)`
//            isRotateGesturesEnabled = true //  If enabled, users can use a two-finger rotate gesture to rotate the camera. (enabled by default)`
//            isMyLocationButtonEnabled = true // If the button is enabled, The my-location button causes the camera to move such that the user's location is in the center of the map. it is only shown when the my-location layer is enabled. (enabled by default)
//            isMapToolbarEnabled = true // If enabled, and the Map Toolbar can be shown in the current context, users will see a bar with various context-dependent actions, including 'open this map in the Google Maps app' and 'find directions to the highlighted marker in the Google Maps app' (enabled by default)
//            isCompassEnabled = true //The compass is an icon on the map that indicates the direction of north on the map. If enabled, it is only shown when the camera is tilted or rotated away from its default orientation (tilt of 0 and a bearing of 0). (0enabled by default)


// video: 6 (map padding)
// with map padding we can specify how many pixels we want to move those ui controllers away from fragment map container
// the center point will move to thw left side when use -> map.setPadding(0,0,300,0)
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

// video: 9 (about camera position):
//google maps sdk allows use to change user's view port of the map by modifying camera position
// so changes of the camera will not make any changes to markers, overlays and other graphics we can use
// the map view is modeled as a camera looking down to a flat surface (90 degree angle)
// the position of the camera is specified by multiple properties:
// 1) Target = location
// 2) Bearing = orientation
// 3) Tilt = viewing Angle
// 4) Zoom: Determines Scale of the Map
// use getMinZoomLevel() function to check for minimum zoom level on a specific device as it may varies over devices

// video: 10 (show building in 3d on map)
//     val cairo: CameraPosition = CameraPosition.Builder()
//        .target(LatLng(30.05114940018266, 31.235459175307987))
//        .zoom(17f)
//        .bearing(0f) // towards north
//        .tilt(45f) // viewing angle of 45 degree
//        .build()

// video: 11 (Change Zoom Level and set MaxMin zoom level)
// set min zoom level -> map.setMinZoomPreference(15f)
// set max zoom level -> map.setMaxZoomPreference(15f)
// CameraUpdateFactory.zoomIn() // zoom in by only one level
// CameraUpdateFactory.zoomOut() // zoom out by only one level
// CameraUpdateFactory.zoomTo(12) // zoom to a specific level (to level 12)
// CameraUpdateFactory.zoomBy(3f) // zoom by a specific amount of zoom levels ( 12+3=15)

// video: 12 (update camera position programmatically)
// and scrolling the postiion of the camera by shifting the center of the view with the specified number of pixels whether on x or y axis
// scrollBy() method: let us change camera position with a specific number of pixels on x and y axis
// +ve in x-axis -> go right (scroll left)
// -ve in x-axis -> go left (scroll right)
// +ve in y-axis -> go down (scroll down)
// -ve in y-axis -> go up (scroll up)

// video: 13 (set boundaries on the map)
// newLatLngBound() function: which lets us specifies the location boundaries we want to display on the map
// ( we can only call this function when map layout completed bec apis calculates the display boundaries of the map during layout )
//  map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.gizaBoundaries, 0))
//  map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewPort.gizaBoundaries.center, 10f)) (with in the boundaries)

// video: 14 (restrict user from scrolling)
// the user will be able to move through this boundaries only
// map.setLatLngBoundsForCameraTarget(cameraAndViewPort.gizaBoundaries)

// video: 15 (animate camera movement)
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

// video: 16 (single and long click listener):
// can be helpful when we use with those markers (ex: add new marker when user long Clicked )
// map.setOnMapClickListener {} for single click
//  map.setOnMapLongClickListener {} for long click

// video: 17 (Markers):
// markers used to identify the location on the map and default marker used standard icon
// we can customize marker properties like (color, image, opacity, anchor point, rotation, z-index, tag ...)
// markers are interactive and they receive click events by default (single click open info window)

// video: 18 (store data object in a marker):
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

// video: 19 (enable marker dragging): "Disabled by default"
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

// video: 20 (marker customization):
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

// video: 21 (marker zIndex):(default zIndex value = 0)
// zIndex: it specifies the stack order of the marker relative to other markers on the map
// a marker with higher zIndex will be drawn on the top of other markers with lower zIndex
// note: markers always drawn above other layers and overlays (ground, polygons and other shapes)
// so markers are considered to be a separate zIndex group compared to other overlays
//   .zIndex(1f)) // now it will be on top the first marker when there is an overlay

// video: 21 (info window):
// allows you to display information to the user when they tab on the marker
// if we have one info window open on a marker and we tried to open another info window of another marker the first one will close automatically
// to add description to info window: .snippet("hello from cairo!")
// to make info window not appear we have to make onMarkerClick function return true overwritten the default behaviour of the marker

// video: 22 (Custom info window):
// 1- design custom layout
// 2- implement CustomInfoWindowAdapter
// 3- map.setInfoWindowAdapter(CustomInfoAdapter(this))

// video: 23 (introduction to polyline):
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