package de.hs_hannover.fholz.studentenklatschen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.hs_hannover.fholz.studentenklatschen.Login.LoginActivity;
import de.hs_hannover.fholz.studentenklatschen.Menu.AccelTest;
import de.hs_hannover.fholz.studentenklatschen.Menu.Hauptmenue;
import de.hs_hannover.fholz.studentenklatschen.Shop.ItemGenerator;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton2 = (Button)findViewById(R.id.button2);
        Button myButton3 = (Button)findViewById(R.id.button3);
        Button myButton4 = (Button)findViewById(R.id.button4);
        Button myButton5 = (Button)findViewById(R.id.button9);
        Button myButton6 = (Button)findViewById(R.id.button6);
        Button myButton7 = (Button)findViewById(R.id.button10);

        myButton2.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, QuestCat.class);
                        startActivity(I);
                    }
                }
        );

        myButton6.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, AccelTest.class);
                        startActivity(I);
                    }
                }
        );

        myButton3.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(I);
                    }
                }
        );

        myButton4.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, ItemGenerator.class);
                        startActivity(I);
                    }
                }
        );

        myButton5.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, Hauptmenue.class);
                        startActivity(I);
                    }
                }
        );

        myButton7.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MainActivity.this, Travel.class);
                        startActivity(I);
                    }
                }
        );
    }

}
