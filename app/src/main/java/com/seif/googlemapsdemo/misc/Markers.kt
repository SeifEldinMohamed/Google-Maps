package com.seif.googlemapsdemo.misc

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class Markers {

//    private fun fromVectorToBitmap(id:Int, color:Int): BitmapDescriptor {
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
}