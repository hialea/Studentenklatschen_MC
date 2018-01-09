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
    int earnedKlatschis = 0;

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
            timerHandler.postDelayed(this, 500);
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
                    final int value=0;
                    timerHandler.removeCallbacks(timerRunnable);

                    playerRef.child("character").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int k = value;
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                String location = snap.getValue().toString();
                                Log.d("Locations updated", "location: " + location);
                            }

                            k = ((Long) dataSnapshot.child("inventory").child("klatschis").getValue()).intValue();
                            Log.d("asdfghjklö", String.valueOf(value));
                            playerRef.child("character").child("inventory").child("klatschis").setValue(earnedKlatschis+k);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }



                    });

                    viewButton.setText("start");

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
