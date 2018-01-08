package de.hs_hannover.fholz.studentenklatschen.Menu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Hauptmenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenue);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_kampf:
                        Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_reise:
                        Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_kampf:
                        Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_reise:
                        Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        Button profil = (Button)findViewById(R.id.profil);
        Button kampf = (Button)findViewById(R.id.kampf);
        Button vl = (Button)findViewById(R.id.vl);
        Button settings = (Button)findViewById(R.id.einstellungen);
        Button about = (Button)findViewById(R.id.about);
        Button credits = (Button)findViewById(R.id.credits);

        profil.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, Profil.class);
                        startActivity(I);
                    }
                }
        );

        kampf.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, Kampf.class);
                        startActivity(I);
                    }
                }
        );

        vl.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, Vorlesung.class);
                        startActivity(I);
                    }
                }
        );

        settings.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, Einstellungen.class);
                        startActivity(I);
                    }
                }
        );

        about.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, About.class);
                        startActivity(I);
                    }
                }
        );

        credits.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, Credits.class);
                        startActivity(I);
                    }
                }
        );
    }
}
