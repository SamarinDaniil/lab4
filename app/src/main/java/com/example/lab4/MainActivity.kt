package com.example.lab4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    var manager:SensorManager? = null
    var current_degree:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this,manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var degree:Int = event?.values?.get(0)?.toInt() !!
        val textView: TextView = findViewById(R.id.txDegrees)
        val imageView: ImageView = findViewById(R.id.imMovCompas)
        textView.text = degree.toString()
        var rotateAnim = RotateAnimation(current_degree.toFloat(),(-degree.toFloat()),
            Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f )
        rotateAnim.duration = 210
        rotateAnim.fillAfter = true
        current_degree = -degree
        imageView.startAnimation(rotateAnim)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}