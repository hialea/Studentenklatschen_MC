package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;

public class Hauptmenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenue);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Hauptmenue.this, Profil.class);
                        startActivity(intent1);
                        //Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_kampf:
                        Intent intent2 = new Intent (Hauptmenue.this, Kampf.class);
                        startActivity(intent2);
                        //Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_reise:
                        Intent intent3 = new Intent (Hauptmenue.this, Vorlesung.class);
                        startActivity(intent3);
                        //Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Hauptmenue.this, Shop.class);
                        startActivity(intent4);
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
