package de.hs_hannover.fholz.studentenklatschen.Travel;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.*;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Affix;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Character;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.R;

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
                            playerRef.child("character").child("inventory").child("klatschis").setValue((earnedKlatschis*level)+klatschis);
                            if(earnedKlatschis >= 2){
                                Item item = new Item(level);

                                RelativeLayout mainLayout = (RelativeLayout)
                                        findViewById(R.id.layout_travel);
                                LayoutInflater inflater = (LayoutInflater)
                                        getSystemService(LAYOUT_INFLATER_SERVICE);
                                View popupView = inflater.inflate(R.layout.item_window, null);

                                int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                                int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                                boolean focusable = true; // lets taps outside the popup also dismiss it
                                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                                popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                                popupView.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        popupWindow.dismiss();
                                        return true;
                                    }
                                });
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
