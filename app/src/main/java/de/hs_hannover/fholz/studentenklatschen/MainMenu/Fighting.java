package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Fighting extends AppCompatActivity implements SensorEventListener {

    TextView counter, task, lp;
    private SensorManager sensorManager;
    float xAccel, yAccel = 0.0f;
    float xMax, xMin, yMax, yMin;
    int lifepoints = 100;
    Random rn = new Random();
    int chosenChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);;
        initializeView();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        task.setText("Follow the instructions in:");
        countDownPause(5, counter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = sensorEvent.values[0];
            yAccel = sensorEvent.values[1];
        }
        maxValues();
    }

    public void countDown(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                displayTime.setText(String.format("%d", seconds));
            }

            public void onFinish() {
                displayTime.setText("0");
                challenge(chosenChallenge);
                if (lifepoints > 0) {
                    countDownPause(3, counter);
                } else {
                    task.setText("Gewonnen");
                }
            }
        }.start();
    }

    public void countDownPause(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                displayTime.setText(String.format("%d", seconds));
            }

            public void onFinish() {
                displayTime.setText("0");
                challengetxt();
                countDown(5, counter);
            }
        }.start();
    }

    public void challengetxt() {
        int rchallenge = rn.nextInt(3);
        switch (rchallenge) {
            case 0:
                task.setText(R.string.challengeUpsideDown);
                break;
            case 1:
                task.setText(R.string.challengeLeft);
                break;
            case 2:
                task.setText(R.string.challengeRight);
                break;
            default:
                task.setText(R.string.challengeUpsideDown);
                break;
        }
        chosenChallenge = rchallenge;
    }

    public void challenge(int rchallenge) {
        switch (rchallenge) {
            case 0:
                challengeUpDown();
                break;
            case 1:
                challengeLeft();
                break;
            case 2:
                challengeRight();
                break;
            default:
                challengeUpDown();
                break;
        }
    }

    public void challengeUpDown () {
        task.setText(R.string.challengeUpsideDown);
        if (yMin < -8) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
        yMin = 0;
    }

    public void challengeLeft () {
        task.setText(R.string.challengeLeft);
        if (xMin < -3) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
        xMin= 0;
    }

    public void challengeRight () {
        task.setText(R.string.challengeRight);
        if (xMax > 3) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
        xMax = 0;
    }

    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        lp = (TextView) findViewById(R.id.lp);
    }

    public void maxValues() {
        if (xAccel > xMax) {
            xMax = xAccel;
        }
        if (xAccel < xMin) {
            xMin = xAccel;
        }
        if (yAccel > yMax) {
            yMax = yAccel;
        }
        if (yAccel < yMin) {
            yMin = yAccel;
        }
    }
}
