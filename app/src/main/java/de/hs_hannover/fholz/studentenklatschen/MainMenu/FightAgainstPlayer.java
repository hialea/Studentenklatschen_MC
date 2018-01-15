package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;

/*public class FightAgainstPlayer implements SensorEventListener {
    private TextView counter, task, enemylp, swipe, ownLp;
    private SensorManager sensorManager;
    private float xAccel, yAccel, zAccel = 0.0f;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private int ownLifepoints = 100;
    private int enemyLifepoints = 100;
    Random rn = new Random();
    private int chosenChallenge, rchallenge, damage;
    private boolean right, left, up, down;
    public Vibrator v;
    private int ownLevel = 1;
    private int enemyLevel = 1;
    boolean sp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        initializeView();
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

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

    public void countDownSp(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                displayTime.setVisibility(View.INVISIBLE);
                if (sp = true) {
                    challengetxt();
                } else {
                    task.setText("Your enemys turn")
                }
            }

            public void onFinish() {
                if (sp = true) {
                    challenge(chosenChallenge, ownLevel, enemyLifepoints);
                    if (enemyLifepoints > 0) {
                        clearValues();
                        countDownSp(2, counter);
                    } else {
                        task.setText("You won");
                    }
                } else {
                    if (ownLifepoints > 0) {
                        rInt();
                        challengetxt();
                        clearValues();
                        countDownSp(2, counter);
                    } else {
                        task.setText("You lose");
                    }
                }
                sp = !sp;
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
                if (sp = true) {
                    rInt();
                    challengetxt();
                    clearValues();
                    countDownSp(2, counter);
                } else {
                    task.setText("Your enemys turn")
                }
            }
        }.start();
    }

    public void strike(int spLevel, int spLifepoints) {
        damage = (rn.nextInt(10) + 1 + rn.nextInt(spLevel));
        task.setText("Your enemys damage: " + damage);
        spLifepoints = spLifepoints - damage;
    }

    public void rInt() {
        rchallenge = rn.nextInt(9);
        if (rchallenge == chosenChallenge) {
            rchallenge = rn.nextInt(9);
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
            case 7:
                task.setText(R.string.challengeSwipeUp);
                break;
            case 8:
                task.setText(R.string.challengeSwipeDown);
                break;
            default:
                task.setText(R.string.challengeUpsideDown);
                break;
        }
    }

    public void challenge(int rchallenge, int spLevel, int spLifepoints) {
        switch (rchallenge) {
            case 0:
                challengeUpDown(spLevel, spLifepoints);
                break;
            case 1:
                challengeLeft(spLevel, spLifepoints);
                break;
            case 2:
                challengeRight(spLevel, spLifepoints);
                break;
            case 3:
                challengeShake(spLevel, spLifepoints);
                break;
            case 4:
                challengeHold(spLevel, spLifepoints);
                break;
            case 5:
                challengeSwipeLeft(spLevel, spLifepoints);
                break;
            case 6:
                challengeSwipeRight(spLevel, spLifepoints);
                break;
            case 7:
                challengeSwipeUp(spLevel, spLifepoints);
                break;
            case 8:
                challengeSwipeDown(spLevel, spLifepoints);
                break;
            default:
                challengeUpDown(spLevel, spLifepoints);
                break;
        }
    }

    public void challengeUpDown (int spLevel, int spLifepoints) {
        if (yMin < -8) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeLeft (int spLevel, int spLifepoints) {
        if (xMax > 3) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeRight (int spLevel, int spLifepoints) {
        if (xMin < -3) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeShake (int spLevel, int spLifepoints) {
        if (xMax > 15 && xMin < -15 && yMax > 15 && yMin < -15 && zMax > 15 && zMin < -15) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeHold (int spLevel, int spLifepoints) {
        if (xMax > xAccel -2 && xAccel + 2 > xMax && yMax > yAccel -2 && yAccel + 2 > yMax) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeLeft (int spLevel, int spLifepoints) {
        if (left == true) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeRight (int spLevel, int spLifepoints) {
        if (right == true) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeUp (int spLevel, int spLifepoints) {
        if (up == true) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeDown (int spLevel, int spLifepoints) {
        if (down == true) {
            strike(spLevel, spLifepoints);
        } else {
            task.setText(R.string.miss);
        }
    }

    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        enemylp = (TextView) findViewById(R.id.lp);
        enemylp.setText("enemy lifepoints: " + enemyLifepoints +  " Level: " + enemyLevel);
        ownLp = (TextView) findViewById(R.id.ownLp);
        ownLp.setText("your lifepoints: " + ownLifepoints + " Level: " + ownLevel);
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
}*/
