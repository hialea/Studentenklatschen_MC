package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel2;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_credits);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Credits.this, Profil.class);
                        startActivity(intent1);
                        //Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (Credits.this, GeneratedEnemy.class);
                        startActivity(intent2);
                        //Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (Credits.this, Travel2.class);
                        startActivity(intent3);
                        //Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Credits.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}
