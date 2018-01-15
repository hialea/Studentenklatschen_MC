package de.hs_hannover.fholz.studentenklatschen.Travel;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.*;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;
import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;

public class Travel extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;
    int earnedKlatschis = -1;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            if(seconds%10==0){
                earnedKlatschis++;
                Log.d("myyyyTag", String.valueOf(earnedKlatschis));
            }
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        timerTextView = (TextView) findViewById(R.id.travel_timer_view);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_travel);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Travel.this, Profil.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (Travel.this, Fighting.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (Travel.this, Travel.class);
                        startActivity(intent3);
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Travel.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        Button travelLogButton = (Button) findViewById(R.id.travel_log_button);
        travelLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent (Travel.this, TravelLog.class);
                startActivity(intent5);
            }
        });

        Button travelToggleButton = (Button) findViewById(R.id.travel_go_button);
        travelToggleButton.setText("start");
        travelToggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button viewButton = (Button) v;
                if (viewButton.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    viewButton.setText("start");
                    playerRef.child("character").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int klatschis, level;
                            klatschis = ((Long) dataSnapshot.child("inventory").child("klatschis").getValue()).intValue();
                            level = ((Long) dataSnapshot.child("level").getValue()).intValue();
                            Log.d("asdfghjklö", String.valueOf(klatschis));
                            Log.d("asdfghjklö", String.valueOf(level));
                            Log.d("asdfghjklö", String.valueOf(earnedKlatschis));
                            charRef.child("inventory").child("klatschis").setValue((earnedKlatschis*level)+klatschis);
                            if(earnedKlatschis >= 1){
                                Item item = new Item(level);

                                /*RelativeLayout mainLayout = (RelativeLayout)
                                        findViewById(R.id.layout_travel);
                                LayoutInflater inflater = (LayoutInflater)
                                        getSystemService(LAYOUT_INFLATER_SERVICE);
                                View popupView = inflater.inflate(R.layout.item_window, null);

                                boolean focusable = true;
                                final PopupWindow popupWindow = new PopupWindow(popupView, 600, 600, focusable);

                                popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                                popupView.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        popupWindow.dismiss();
                                        return true;
                                    }
                                });*/
                            }
                            earnedKlatschis = -1;
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    viewButton.setText("stop");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.travel_go_button);
        b.setText("start");
    }
}
