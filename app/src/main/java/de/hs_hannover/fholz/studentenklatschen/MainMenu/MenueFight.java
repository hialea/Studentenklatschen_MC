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
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class MenueFight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_fight);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_kampf);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (MenueFight.this, Profil.class);
                        startActivity(intent1);
                        //Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (MenueFight.this, MenueFight.class);
                        startActivity(intent2);
                        //Toast.makeText(Hauptmenue.this, "Action MenueFight Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (MenueFight.this, Travel.class);
                        startActivity(intent3);
                        //Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (MenueFight.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        Button sop = (Button)findViewById(R.id.search_other_player);
        Button ge = (Button)findViewById(R.id.gererate_enemy);
        sop.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MenueFight.this, FightAgainstPlayer.class);
                        startActivity(I);
                    }
                }
        );

        ge.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(MenueFight.this, GeneratedEnemy.class);
                        startActivity(I);
                    }
                }
        );
    }
}