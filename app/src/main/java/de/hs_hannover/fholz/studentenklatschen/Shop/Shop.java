package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.hs_hannover.fholz.studentenklatschen.MainMenu.CharacterProfile;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.GeneratedEnemy;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.MenueFight;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;
import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

public class Shop extends AppCompatActivity {

    private TextView klatschiTv;
    private int klatschis;

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
                        Intent intent1 = new Intent (Shop.this, CharacterProfile.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (Shop.this, MenueFight.class);
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

        klatschiTv = (TextView) findViewById(R.id.muenzen);

        playerRef.child("character").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                klatschis = ((Long) dataSnapshot.child("inventory").child("klatschis").getValue()).intValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        klatschiTv.setText(klatschis);


    }


}


//List ItemListe = new ArrayList<String>();
//ListAdapter profilAdapter = new ArrayAdapter<String>(ItemListe);