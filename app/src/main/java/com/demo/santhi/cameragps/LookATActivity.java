package com.demo.santhi.cameragps;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Jaladi Udaya Santhi on 11-07-2018.
 */

public class LookATActivity implements SensorEventListener {
    Context appContext = null;

    public static String getLookAtText() {
        return lookAtText;
    }

    public void setLookAtText(String lookAtText) {
        this.lookAtText = lookAtText;
    }

    public static String lookAtText;

    public LookATActivity(Context context){
        appContext = context;
    }
    float[] mGravity;
    float[] mGeomagnetic;
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                /*float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                //1 Radian = 57.2957795f degrees
                float azimut = Math.round(orientation[0] * 57.2957795f);
                float pitch = Math.round(orientation[1]  * 57.2957795f);
                float roll = Math.round(orientation[2] * 57.2957795f);
                //normalise these angles

                String lookAtString = "azimuth: " + azimut + " pitch: " + pitch + " roll: " + roll;*/
                String lookAtString = "x: "+ (-1* R[2]) +" y: " +(-1 * R[5])+ " z: "+(-1 * R[8]);
                setLookAtText(lookAtString);
                //Toast.makeText(appContext, lookAtString, Toast.LENGTH_SHORT).show();
                // orientation contains: azimut, pitch and roll
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
