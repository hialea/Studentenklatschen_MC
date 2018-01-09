package de.hs_hannover.fholz.studentenklatschen.Travel;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.*;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Character;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory;
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
                            int k, j;
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            }
                            k = ((Long) dataSnapshot.child("inventory").child("klatschis").getValue()).intValue();
                            j = ((Long) dataSnapshot.child("level").getValue()).intValue();
                            Log.d("asdfghjklö", String.valueOf(k));
                            Log.d("asdfghjklö", String.valueOf(j));
                            Log.d("asdfghjklö", String.valueOf(earnedKlatschis));
                            playerRef.child("character").child("inventory").child("klatschis").setValue((earnedKlatschis*j)+k);
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
