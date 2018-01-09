package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;
import java.util.TreeMap;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Affix;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.DEFENSE;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.SPECIAL;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.STRENGTH;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.attributeName;

public class Shop extends AppCompatActivity {

    TreeMap<String, Integer> tm = new TreeMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        Random rand = new Random();
        int rndSlot = rand.nextInt(4);
        int itemlvl = 2;
        Affix affix = Affix.genAffix();

        Item item1 = new Item(rndSlot, itemlvl, affix);
        tm.put(hallo, item1.attributes.get(attributeName[STRENGTH]), item1.attributes.get(attributeName[DEFENSE]), item1.attributes.get(attributeName[SPECIAL]));

        Item item2 = new Item(rndSlot, itemlvl, affix);
        tm.put(ha, item2.attributes.get(attributeName[STRENGTH]), item2.attributes.get(attributeName[DEFENSE]), item2.attributes.get(attributeName[SPECIAL]));

        Item item3 = new Item(rndSlot, itemlvl, affix);
        tm.put(hu, item3.attributes.get(attributeName[STRENGTH]), item3.attributes.get(attributeName[DEFENSE]), item3.attributes.get(attributeName[SPECIAL]));

        Item item4 = new Item(rndSlot, itemlvl, affix);
        tm.put(he, item4.attributes.get(attributeName[STRENGTH]), item4.attributes.get(attributeName[DEFENSE]), item4.attributes.get(attributeName[SPECIAL]));


    }


}
