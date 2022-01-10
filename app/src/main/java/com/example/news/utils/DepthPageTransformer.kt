package com.example.news.utils

import android.opengl.ETC1
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import android.opengl.ETC1.getHeight

import android.opengl.ETC1.getWidth
import android.util.Log

import android.view.MotionEvent


private const val MIN_SCALE = 0.75f

class DepthPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width

//            Log.e("Position:::: ", position.toString())

            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                    Log.e("trans - 1 ::: ", "$translationX $translationY $translationZ")
                }
                position <= 0 -> { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    alpha = 1f
                    scaleY = 1f
                    scaleX = 1f
                    Log.e("trans - 1 -- 0 ::: ", "$translationX $translationY $translationZ")
                }
                position <= 1 -> { // (0,1]
                    // Fade the page out.
                    Log.e("trans 0 -- 1 ::: ", "$translationX $translationY $translationZ")
                    alpha = 1 - position
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleY = scaleFactor
                    scaleX = scaleFactor
//                    pivotY = -( 1 - scaleFactor)
                }
                else -> { // (1,+Infinity]
                    Log.e("trans 1 ::: ", "$translationX $translationY $translationZ")
                    alpha = 0f
//                    pivotY = -5f
                }
            }
        }
    }


//    private fun swapXY(ev: MotionEvent): MotionEvent? {
//       ev.get
//        val width = getWidth().toFloat()
//        val height = getHeight().toFloat()
//        val newX = ev.y / height * width
//        val newY = ev.x / width * height
//        ev.setLocation(newX, newY)
//        return ev
//    }
}