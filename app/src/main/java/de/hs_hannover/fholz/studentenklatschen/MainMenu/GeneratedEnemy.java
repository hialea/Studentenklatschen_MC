package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;

public class GeneratedEnemy extends AppCompatActivity  implements SensorEventListener {
    private TextView counter, task, lp, swipe, ownLp;
    private SensorManager sensorManager;
    private float xAccel, yAccel, zAccel = 0.0f;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private int spLifepoints = 100;
    private int geLifepoints = 100;
    Random rn = new Random();
    private int chosenChallenge, rchallenge, geDamage, spDamage, geLevel;
    private boolean right, left, up, down;
    public Vibrator v;
    private int spLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        generateLevel();
        initializeView();
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (GeneratedEnemy.this, Profil.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (GeneratedEnemy.this, Fighting.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (GeneratedEnemy.this, GeneratedEnemy.class);
                        startActivity(intent3);
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (GeneratedEnemy.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        swipe.setOnTouchListener(new OnSwipeTouchListener(GeneratedEnemy.this) {
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
                displayTime.setVisibility(View.INVISIBLE);
                challengetxt();
            }

            public void onFinish() {
                challenge(chosenChallenge);
                if (geLifepoints > 0) {
                    clearValues();
                    countDownEnemy(3, counter);
                } else {
                    task.setText("Gewonnen");
                }

            }
        }.start();
    }

    public void countDownEnemy(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                rInt();
                challengetxt();
                generateAttack();
                if (spLifepoints > 0) {
                    clearValues();
                    countDown(3, counter);
                } else {
                    task.setText("Verloren");
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
                rInt();
                challengetxt();
                clearValues();
                countDown(1, counter);
            }
        }.start();
    }

    public void rInt() {
        rchallenge = rn.nextInt(7);
        if (rchallenge == chosenChallenge) {
            rchallenge = rn.nextInt(7);
        }
        chosenChallenge = rchallenge;
    }

    public void challengetxt() {
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
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeLeft () {
        if (xMax > 3) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeRight () {
        if (xMin < -3) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeShake () {
        if (xMax > 20 && xMin < -20 && yMax > 20 && yMin < -20 && zMax > 20 && zMin < -20) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeHold () {
        if (xMax > xAccel -2 && xAccel + 2 > xMax && yMax > yAccel -2 && yAccel + 2 > yMax) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeLeft () {
        if (left == true) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeRight () {
        if (right == true) {
            treffer();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void treffer () {
        geDamage = (rn.nextInt(10) + 1 + rn.nextInt(spLevel));
        task.setText("Schaden: " + geDamage);
        geLifepoints = geLifepoints - geDamage;
        lp.setText("enemy lifepoints: " + geLifepoints);
    }

    public void generateLevel () {
        geLevel = (rn.nextInt(4) + spLevel);
    }

    public void generateAttack () {
        spDamage = (rn.nextInt(10) + rn.nextInt(geLevel));
        spLifepoints = spLifepoints - spDamage;
        ownLp.setText("your lifepoints: " + spLifepoints);
        if (spDamage > 0) {
            v.vibrate(100);
        }
    }

    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        lp = (TextView) findViewById(R.id.lp);
        lp.setText("enemy lifepoints: " + geLifepoints + " Level: " + geLevel);
        ownLp = (TextView) findViewById(R.id.ownLp);
        ownLp.setText("your lifepoints: " + spLifepoints + " Level: " + spLevel);
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
