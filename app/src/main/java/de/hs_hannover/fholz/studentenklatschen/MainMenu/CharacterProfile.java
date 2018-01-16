package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;
import de.hs_hannover.fholz.studentenklatschen.Travel.TravelLog;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.charRef;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

public class CharacterProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_profile);

        Button inventory = (Button) findViewById(R.id.char_inv);
        Button profil = (Button) findViewById(R.id.char_profil);

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CharacterProfile.this, CharacterInventory.class);
                startActivity(intent);
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CharacterProfile.this, Profil.class);
                startActivity(intent);
            }
        });


        playerRef.child("character").addListenerForSingleValueEvent(new ValueEventListener() {

            TextView charLife = (TextView) findViewById(R.id.char_life);
            TextView charRolle = (TextView) findViewById(R.id.char_rolle);
            TextView charLevel = (TextView) findViewById(R.id.char_level);
            TextView charExp = (TextView) findViewById(R.id.char_exp);
            TextView charStr = (TextView) findViewById(R.id.char_str);
            TextView charDef = (TextView) findViewById(R.id.char_def);
            TextView charSp = (TextView) findViewById(R.id.char_sp);

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                charLife.setText(getResources().getString(R.string.charLife) + " " +((Long) dataSnapshot.child("lifepoints").getValue()).intValue());
                charRolle.setText(getResources().getString(R.string.charRole) + " " +(dataSnapshot.child("role").getValue()).toString());
                charLevel.setText(getResources().getString(R.string.charlvl) + " " + ((Long) dataSnapshot.child("level").getValue()).intValue());
                charExp.setText(getResources().getString(R.string.charExp) + " "  + ((Long) dataSnapshot.child("exp").getValue()).intValue());
                charStr.setText(getResources().getString(R.string.attStr) + " " + ((Long) dataSnapshot.child("attributes").child("allAttributes").child("STRENGTH").getValue()).intValue());
                charDef.setText(getResources().getString(R.string.attDef) + " " + ((Long) dataSnapshot.child("attributes").child("allAttributes").child("DEFENSE").getValue()).intValue());
                charSp.setText(getResources().getString(R.string.attSp) + " "  + ((Long) dataSnapshot.child("attributes").child("allAttributes").child("SPECIAL").getValue()).intValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_profil);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (CharacterProfile.this, CharacterProfile.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (CharacterProfile.this, GeneratedEnemy.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (CharacterProfile.this, Travel.class);
                        startActivity(intent3);
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (CharacterProfile.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}
