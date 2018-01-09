package de.hs_hannover.fholz.studentenklatschen.Menu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Fighting extends AppCompatActivity implements SensorEventListener {

    TextView counter, task;
    private float lastX, lastY, lastZ;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);

        task.setVisibility(View.INVISIBLE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
        }

        countDown(5, counter);

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        MaxValues();

        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        if (deltaX < 1)
            deltaX = 0;
        if (deltaY < 1)
            deltaY = 0;
        if (deltaZ < 1)
            deltaZ = 0;

        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];

    }

    public void MaxValues() {
        if (deltaX > deltaXMax) {
            deltaXMax = deltaX;
        }
        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
        }
    }

    public void countDown(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                displayTime.setText(String.format("%d", seconds));
            }

            public void onFinish() {
                displayTime.setVisibility(View.INVISIBLE);
                task.setVisibility(View.VISIBLE);
                task.setText(R.string.challengeUpsideDown);
                challengeUpDown();

            }
        }.start();
    }

    public void challengeUpDown (){
        if (deltaYMax <= (-7)) {
            task.setText("Prima");
        }
    }
}
