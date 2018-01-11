package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;
import java.util.TreeMap;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Affix;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Hauptmenue;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Kampf;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Vorlesung;
import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.DEFENSE;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.SPECIAL;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.STRENGTH;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.attributeName;

public class Shop extends AppCompatActivity {

    TreeMap<String, Integer> tm = new TreeMap<>();

    /*in einer treemap hast du immer ein paar aus 1 schlüsselwort und 1 objekt. du versuchst aber da drei objekte reinzuschieben
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Shop.this, Profil.class);
                        startActivity(intent1);
                        //Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_kampf:
                        Intent intent2 = new Intent (Shop.this, Kampf.class);
                        startActivity(intent2);
                        //Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_reise:
                        Intent intent3 = new Intent (Shop.this, Vorlesung.class);
                        startActivity(intent3);
                        //Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Shop.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });


        int itemlvl = 2;


        Item item1 = new Item(itemlvl);

        /*tm.put(hallo, item1.attributes.get(attributeName[STRENGTH]), item1.attributes.get(attributeName[DEFENSE]), item1.attributes.get(attributeName[SPECIAL]));

        Item item2 = new Item(rndSlot, itemlvl, affix);
        tm.put(ha, item2.attributes.get(attributeName[STRENGTH]), item2.attributes.get(attributeName[DEFENSE]), item2.attributes.get(attributeName[SPECIAL]));

        Item item3 = new Item(rndSlot, itemlvl, affix);
        tm.put(hu, item3.attributes.get(attributeName[STRENGTH]), item3.attributes.get(attributeName[DEFENSE]), item3.attributes.get(attributeName[SPECIAL]));

        Item item4 = new Item(rndSlot, itemlvl, affix);
        tm.put(he, item4.attributes.get(attributeName[STRENGTH]), item4.attributes.get(attributeName[DEFENSE]), item4.attributes.get(attributeName[SPECIAL]));

        brauchst du nicht*/


    }


}
