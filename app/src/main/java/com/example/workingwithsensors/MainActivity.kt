package com.example.workingwithsensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() , SensorEventListener{
    lateinit var textView: TextView
    private lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {

            sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL)



        } else {
            // Failure! No proximity sensor.
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {

            sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL)



        } else {
            // Failure! No proximity sensor.
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event != null) {
            if(event.sensor.type==Sensor.TYPE_PROXIMITY){
                textView.setText("Values: "+event.values[0])

                if(event.values[0]>0){
                    Toast.makeText(this, "The object is far!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "The object is near!", Toast.LENGTH_SHORT).show()

                }
            }

            if(event.sensor.type==Sensor.TYPE_GYROSCOPE){
               // textView.setText("Values: "+event.values[0])

                if(event.values[0]>0.5f){
                    window.decorView.setBackgroundColor(Color.BLUE)
                     }
                else if (event.values[0]<-0.5f){
                    window.decorView.setBackgroundColor(Color.YELLOW)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //test
        println("Accuracy changed: "+accuracy)
    }
}