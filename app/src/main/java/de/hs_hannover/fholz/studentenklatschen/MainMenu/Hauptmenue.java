package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import de.hs_hannover.fholz.studentenklatschen.MainActivity;
import de.hs_hannover.fholz.studentenklatschen.QuestCat;
import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class Hauptmenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenue);

        Button lol = (Button)findViewById(R.id.about);
//dss
        lol.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Hauptmenue.this, About.class);
                        startActivity(I);
                    }
                }
        );


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.action_profil:
                    Intent intent1 = new Intent (Hauptmenue.this, Profil.class);
                    startActivity(intent1);
                    break;

                case R.id.action_fight:
                    Intent intent2 = new Intent (Hauptmenue.this, GeneratedEnemy.class);
                    startActivity(intent2);
                    break;

                case R.id.action_travel:
                    Intent intent3 = new Intent (Hauptmenue.this, Travel.class);
                    startActivity(intent3);
                    break;

                case R.id.action_shop:
                    Intent intent4 = new Intent (Hauptmenue.this, Shop.class);
                    startActivity(intent4);
                    break;
            }
            return false;
            }
        });
    }
}
