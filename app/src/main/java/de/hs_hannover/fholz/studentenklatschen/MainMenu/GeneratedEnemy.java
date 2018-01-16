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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;

public class GeneratedEnemy extends AppCompatActivity  implements SensorEventListener {
    private TextView counter, task, lp, ownLp, level, ownLevel;
    private ImageView swipe;
    private SensorManager sensorManager;
    private float xAccel, yAccel, zAccel = 0.0f;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private int spLifepoints = 100;
    private int geLifepoints = 100;
    Random rn = new Random();
    private int chosenChallenge, rchallenge, geDamage, spDamage, geLevel;
    private boolean right, left;
    public Vibrator v;
    private int spLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        generateLevel();
        initializeView();

        //Listener für Touchaktionen
        swipe.setOnTouchListener(new OnSwipeTouchListener(GeneratedEnemy.this) {
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

    //Registriert Accelerometer
    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    //Stoppt Accelerometer
    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Anweisung, was bei der Änderung des Accelerometers passiert
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = sensorEvent.values[0];
            yAccel = sensorEvent.values[1];
            zAccel = sensorEvent.values[2];
        }
        maxValues();
    }

    //Erkennung der maximal erreichten Werte des Accelerometers
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

    //Bereinigung des Accelerometerwerte
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

    //Countdown für den Start der Vorgänge
    public void countDownStart(int Seconds, final TextView displayTime){

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

    //Countdown für den Spieler
    //Prüft nach Ablauf der Zeit, ob Bedingung der Challenge erfüllt wurde
    //Prüft ob das Spiel noch weiter geführt werden muss
    public void countDown(int Seconds, final TextView displayTime){

        //final MediaPlayer screamWon = MediaPlayer.create(this, R.raw.screamwon);
        //final MediaPlayer fightsound = MediaPlayer.create(this, R.raw.fightsound);

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                displayTime.setVisibility(View.INVISIBLE);
                challengetxt();
                //fightsound.start();
            }

            public void onFinish() {
                challenge(chosenChallenge);
                if (geLifepoints > 0) {
                    clearValues();
                    countDownEnemy(1);
                } else {
                    task.setText(R.string.won);
                    //screamWon.start();
                }

            }
        }.start();
    }

    //Countdown während der generierte Gegner seinen Angriff ausführt
    //Prüft ob das Spiel noch weiter geführt werden muss
    public void countDownEnemy(int Seconds){

        //final MediaPlayer screamLost = MediaPlayer.create(this, R.raw.screamlost);

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                generateAttack();
                if (spLifepoints > 0) {
                    rInt();
                    challengetxt();
                    clearValues();
                    countDown(1, counter);
                } else {
                    task.setText(R.string.lose);
                    //screamLost.start();
                }

            }
        }.start();
    }

    //Funktion die eine zufällige Zahl erstellt damit die Challenge stimmt werden kann
    //Überprüfung, dass möglichst keine Challenge zweimal hintereinander auftritt
    public void rInt() {
        rchallenge = rn.nextInt(7);
        if (rchallenge == chosenChallenge) {
            rchallenge = rn.nextInt(7);
        }
        chosenChallenge = rchallenge;
    }

    //Setzt den Text für die Aufgabe
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

    //Ruft die entsprechende Challenge zur Prüfung auf
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

    //Challenges
    public void challengeUpDown () {
        if (yMin < -8) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeLeft () {
        if (xMax > 3) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeRight () {
        if (xMin < -3) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeShake () {
        if (xMax > 10 && xMin < -10 && yMax > 10 && yMin < -10 && zMax > 10 && zMin < -10) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeHold () {
        if (xMax > xAccel -2 && xAccel + 2 > xMax && yMax > yAccel -2 && yAccel + 2 > yMax) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeLeft () {
        swipe.setVisibility(View.INVISIBLE);
        if (left == true) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    public void challengeSwipeRight () {
        swipe.setVisibility(View.INVISIBLE);
        if (right == true) {
            strike();
        } else {
            task.setText(R.string.miss);
        }
    }

    //Vorgang beim erfolgreichen ausführen einer Challenge
    public void strike() {
        //final MediaPlayer screamstrike = MediaPlayer.create(this, R.raw.screamstrike);
        //screamstrike.start();
        geDamage = (rn.nextInt(10) + 1 + rn.nextInt(spLevel));
        task.setText("Your enemys damage: - " + geDamage);
        geLifepoints = geLifepoints - geDamage;
        if (geLifepoints < 0) {
            geLifepoints = 0;
            lp.setText("LP: " + geLifepoints);
        } else {
            lp.setText("LP: " + geLifepoints);
        }
    }

    //Generiert das Level des Gegners anhand des eigenen Levels
    public void generateLevel() {
        geLevel = (rn.nextInt(4) + spLevel);
    }

    //Generiert die Attacke des Gegners anhand dessen Level
    public void generateAttack () {
        spDamage = (rn.nextInt(10) + rn.nextInt(geLevel));
        if (spDamage > 0) {
            v.vibrate(100);
        }
        spLifepoints = spLifepoints - spDamage;
        if (spLifepoints < 0) {
            spLifepoints = 0;
            ownLp.setText("LP: " + spLifepoints);
        } else {
            ownLp.setText("LP: " + spLifepoints);
        }
    }

    //Initialisierung der Inhalte des Displays
    public void initializeView() {
        counter = (TextView) findViewById(R.id.timecount);
        task = (TextView) findViewById(R.id.challenge);
        lp = (TextView) findViewById(R.id.lp);
        lp.setText("LP: " + geLifepoints);
        level = (TextView) findViewById(R.id.level);
        level.setText("Level: " + geLevel);
        ownLevel = (TextView) findViewById(R.id.ownLevel);
        ownLevel.setText("Level: " + spLevel);
        ownLp = (TextView) findViewById(R.id.ownLp);
        ownLp.setText("LP: " + spLifepoints);
        swipe =(ImageView) findViewById(R.id.swipe);
        swipe.setVisibility(View.INVISIBLE);
    }
}
