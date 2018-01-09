package de.hs_hannover.fholz.studentenklatschen.Menu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Fighting extends AppCompatActivity {

    TextView counter;
    Boolean timeOut = false;
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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);

        countDown(5, counter);

        if (timeOut) { //in onfinsh rein!
            counter.setVisibility(View.VISIBLE);
            counter.setText("Moin Moin");
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
                timeOut = true;
            }
        }.start();
    }
}
