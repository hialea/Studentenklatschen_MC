package de.hs_hannover.fholz.studentenklatschen.Travel;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Travel extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

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
                    timerHandler.removeCallbacks(timerRunnable);
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
