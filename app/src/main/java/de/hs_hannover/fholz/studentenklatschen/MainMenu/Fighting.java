package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Fighting extends AppCompatActivity implements SensorEventListener {

    private TextView counter, task, lp, swipe;
    private SensorManager sensorManager;
    private float xAccel, yAccel, zAccel = 0.0f;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private int lifepoints = 100;
    Random rn = new Random();
    private int chosenChallenge, rchallenge;
    private boolean right, left, up, down;
    public Vibrator v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);;
        initializeView();
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        swipe.setOnTouchListener(new OnSwipeTouchListener(Fighting.this) {
            public void onSwipeTop() {
                up = true;
            }
            public void onSwipeRight() {
                right = true;
            }
            public void onSwipeLeft() {
                left = true;
            }
            public void onSwipeBottom() {
                down = true;
            }
        });
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        task.setText("Follow the instructions in:");
        countDownPause(3, counter);

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
            zAccel = sensorEvent.values[2];
        }
        maxValues();
    }

    public void countDown(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                displayTime.setVisibility(View.INVISIBLE);
                //displayTime.setText(String.format("%d", seconds));
            }

            public void onFinish() {
                challenge(chosenChallenge);
                if (lifepoints > 0) {
                    challengetxt();
                    clearValues();
                    countDown(2, counter);
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
                challengetxt();
                countDown(3, counter);
                clearValues();
            }
        }.start();
    }

    public void challengetxt() {
        rchallenge = rn.nextInt(7);
        if (rchallenge == chosenChallenge) {
            rchallenge = rn.nextInt(7);
        } else {
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
                case 3:
                    task.setText(R.string.challengeShake);
                    break;
                case 4:
                    task.setText(R.string.challengeHold);
                    break;
                case 5:
                    task.setText(R.string.challengeSwipeLeft);
                    break;
                case 6:
                    task.setText(R.string.challengeSwipeRight);
                    break;
                default:
                    task.setText(R.string.challengeUpsideDown);
                    break;
            }
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
            case 3:
                challengeShake();
                break;
            case 4:
                challengeHold();
                break;
            case 5:
                challengeSwipeLeft();
                break;
            case 6:
                challengeSwipeRight();
                break;
            default:
                challengeUpDown();
                break;
        }
    }

    public void challengeUpDown () {
        if (yMin < -8) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeLeft () {
        if (xMax > 3) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeRight () {
        if (xMin < -3) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeShake () {
        if (xMax > 25 && xMin < -25 && yMax > 25 && yMin < -25 && zMax > 20 && zMin < -20) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeHold () {
        if (xMax > xAccel -2 && xAccel + 2 > xMax && yMax > yAccel -2 && yAccel + 2 > yMax) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeSwipeLeft () {
        if (left == true) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void challengeSwipeRight () {
        if (right == true) {
            task.setText("Prima.");
            lifepoints = lifepoints - 10;
            v.vibrate(100);
            lp.setText("lifepoints: " + lifepoints);
        } else {
            task.setText("Fail.");
        }
    }

    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        lp = (TextView) findViewById(R.id.lp);
        swipe =(TextView) findViewById(R.id.swipe);
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
        if (zAccel > zMax) {
            zMax = zAccel;
        }
        if (zAccel < zMin) {
            zMin = zAccel;
        }
    }

    public void clearValues() {
        xMax = 0;
        yMax = 0;
        xMin= 0;
        yMin = 0;
        zMin= 0;
        zMin = 0;
        right = false;
        left = false;
        up = false;
        down = false;
    }
}
