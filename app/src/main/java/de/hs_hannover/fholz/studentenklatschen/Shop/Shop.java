package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.CharacterProfile;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.MenueFight;
import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.DEFENSE;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.SPECIAL;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.STRENGTH;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.attributeName;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

public class Shop extends AppCompatActivity {

    private TextView klatschiTv;
    private TextView nameWapon, levelWapon, priceWapon, strengthWapon, defenseWapon, specialWapon;
    private TextView nameShirt, levelShirt, priceShirt, strengthShirt, defenseShirt, specialShirt;
    private TextView nameTrousers, levelTrousers, priceTrousers, strengthTrousers, defenseTrousers, specialTrousers;
    private TextView nameShoes, levelShoes, priceShoes, strengthShoes, defenseShoes, specialShoes;
    private int klatschis, ownLevel;

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


        playerRef.child("character").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                klatschis = ((Long) dataSnapshot.child("inventory").child("klatschis").getValue()).intValue();
                ownLevel = ((Long) dataSnapshot.child("level").getValue()).intValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        initializeView();

        /*int itemlvl = ownLevel;
        Item item1 = new Item(itemlvl);
        strengthWapon.setText(String.valueOf(item1.attributes.get(attributeName[STRENGTH])));
        defenseWapon.setText(String.valueOf(item1.attributes.get(attributeName[DEFENSE])));
        specialWapon.setText(String.valueOf(item1.attributes.get(attributeName[SPECIAL])));

        Item item2 = new Item(itemlvl);
        strengthShirt.setText(String.valueOf(item2.attributes.get(attributeName[STRENGTH])));
        defenseShirt.setText(String.valueOf(item2.attributes.get(attributeName[DEFENSE])));
        specialShirt.setText(String.valueOf(item2.attributes.get(attributeName[SPECIAL])));

        Item item3 = new Item(itemlvl);
        strengthTrousers.setText(String.valueOf(item3.attributes.get(attributeName[STRENGTH])));
        defenseTrousers.setText(String.valueOf(item3.attributes.get(attributeName[DEFENSE])));
        specialTrousers.setText(String.valueOf(item3.attributes.get(attributeName[SPECIAL])));

        Item item4 = new Item(itemlvl);
        strengthShoes.setText(String.valueOf(item4.attributes.get(attributeName[STRENGTH])));
        defenseShoes.setText(String.valueOf(item4.attributes.get(attributeName[DEFENSE])));
        specialShoes.setText(String.valueOf(item4.attributes.get(attributeName[SPECIAL])));*/


    }

    public void initializeView() {
        klatschiTv = (TextView) findViewById(R.id.muenzen);
        klatschiTv.setText(String.valueOf(klatschis));
        nameWapon = (TextView) findViewById(R.id.name_wapon);
        nameWapon.setText("Notebook ");
        levelWapon = (TextView) findViewById(R.id.level_wapon);
        priceWapon = (TextView) findViewById(R.id.price_wapon);
        strengthWapon = (TextView) findViewById(R.id.strength_wapon);
        defenseWapon = (TextView) findViewById(R.id.defense_wapon);
        specialWapon = (TextView) findViewById(R.id.special_wapon);
        nameShirt = (TextView) findViewById(R.id.name_shirt);
        nameShirt.setText("T-Shirt ");
        levelShirt = (TextView) findViewById(R.id.level_shirt);
        priceShirt = (TextView) findViewById(R.id.price_shirt);
        strengthShirt = (TextView) findViewById(R.id.strength_shirt);
        defenseShirt = (TextView) findViewById(R.id.defense_shirt);
        specialShirt = (TextView) findViewById(R.id.special_shirt);
        nameTrousers = (TextView) findViewById(R.id.name_trousers);
        nameTrousers.setText("Shorts ");
        levelTrousers = (TextView) findViewById(R.id.level_trousers);
        priceTrousers = (TextView) findViewById(R.id.price_trousers);
        strengthTrousers = (TextView) findViewById(R.id.strength_trousers);
        defenseTrousers = (TextView) findViewById(R.id.defense_trousers);
        specialTrousers = (TextView) findViewById(R.id.special_trousers);
        nameShoes = (TextView) findViewById(R.id.name_shoes);
        nameShoes.setText("Sneaker ");
        levelShoes = (TextView) findViewById(R.id.level_shoes);
        priceShoes = (TextView) findViewById(R.id.price_shoes);
        strengthShoes = (TextView) findViewById(R.id.strength_shoes);
        defenseShoes = (TextView) findViewById(R.id.defense_shoes);
        specialShoes = (TextView) findViewById(R.id.special_shoes);
    }


}


//List ItemListe = new ArrayList<String>();
//ListAdapter profilAdapter = new ArrayAdapter<String>(ItemListe);