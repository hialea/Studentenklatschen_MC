package de.hs_hannover.fholz.studentenklatschen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton2 = (Button)findViewById(R.id.button2);

        myButton2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, QuestCat.class);
                        startActivity(I);
                    }
                }
        );
    }

}
