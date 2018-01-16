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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;

public class FightAgainstPlayer extends AppCompatActivity implements SensorEventListener {

    private TextView counter, task, enemylp, ownLp, enemylevel, ownlevel;
    private ImageView swipe;
    private SensorManager sensorManager;
    private float xAccel, yAccel, zAccel = 0.0f;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private int ownLifepoints = 100;
    private int enemyLifepoints = 100;
    Random rn = new Random();
    private int chosenChallenge, rchallenge, damage;
    private boolean right, left;
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

        swipe.setOnTouchListener(new OnSwipeTouchListener(FightAgainstPlayer.this) {
            public void onSwipeRight() {
                right = true;
            }
            public void onSwipeLeft() {
                left = true;
            }
        });

        task.setText(R.string.startIn);
        countDownStart(5, counter);

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
    }

    public void countDownStart(int Seconds, final TextView displayTime){

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
                    task.setText("Your enemys turn");
                }
            }
        }.start();
    }

    public void countDownSp(int Seconds, final TextView displayTime){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                displayTime.setVisibility(View.INVISIBLE);
                if (sp = true) {
                    challengetxt();
                } else {
                    task.setText("Your enemys turn");
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
                swipe.setVisibility(View.VISIBLE);
                swipe.setImageResource(R.drawable.pfeil_links);
                break;
            case 6:
                task.setText(R.string.challengeSwipeRight);
                swipe.setVisibility(View.VISIBLE);
                swipe.setImageResource(R.drawable.pfeil_rechts);
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

    public void strike(int spLevel, int spLifepoints) {
        damage = (rn.nextInt(10) + 1 + rn.nextInt(spLevel));
        if (damage > 0) {
            v.vibrate(100);
        }
        task.setText("Your enemys damage: " + damage);
        spLifepoints = spLifepoints - damage;
        if (spLifepoints < 0) {
            spLifepoints = 0;
            enemylp.setText("LP: " + spLifepoints);
        } else {
            enemylp.setText("LP: " + spLifepoints);
        }
    }

    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        enemylp = (TextView) findViewById(R.id.lp);
        enemylp.setText("enemy lifepoints: " + enemyLifepoints);
        enemylevel = (TextView) findViewById(R.id.level);
        enemylevel.setText("Level: " + enemyLevel);
        ownLp = (TextView) findViewById(R.id.ownLp);
        ownLp.setText("your lifepoints: " + ownLifepoints);
        ownlevel = (TextView) findViewById(R.id.ownLevel);
        ownlevel.setText("Level: " + ownLevel);
        swipe =(ImageView) findViewById(R.id.swipe);
        swipe.setVisibility(View.INVISIBLE);
    }

}
