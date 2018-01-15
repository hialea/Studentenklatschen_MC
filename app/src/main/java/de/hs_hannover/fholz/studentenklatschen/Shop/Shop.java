package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import de.hs_hannover.fholz.studentenklatschen.MainMenu.GeneratedEnemy;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;
import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class Shop extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_shop);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Shop.this, Profil.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (Shop.this, GeneratedEnemy.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (Shop.this, Travel.class);
                        startActivity(intent3);
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Shop.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });


    }


}


//List ItemListe = new ArrayList<String>();
//ListAdapter profilAdapter = new ArrayAdapter<String>(ItemListe);