package de.hs_hannover.fholz.studentenklatschen.Datamodel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.*;

public class Inventory {

    public static class Slots{
        public static final String[] slotName =
        {
            "Laptop",
            "T-Shirt",
            "Schuhe",
            "Hose"
        };
        public static final int

            LAPTOP=0,
            SHIRT=1,
            BOOTS=2,
            PANTS=3;
    }

    public final int INV_SIZE = 10;
    public int slotsTaken;
    public ArrayList<Item> invContents;
    public Map<String, Item> equippedItems;

    public Inventory(){
        slotsTaken = 0;
        equippedItems = new HashMap<String, Item>() {
            {
                put(slotName[LAPTOP], null);
                put(slotName[SHIRT], null);
                put(slotName[BOOTS], null);
                put(slotName[PANTS], null);
            }
        };
        invContents = new ArrayList<>();
    }
   /* public boolean obtainItem(Item item){
        for(int i=0; i < invContents.length; i++)
        {
            if(invContents[i]==null) {
                invContents[i] = item;
                return true;
            }
        }
        return false; // TODO inventar voll ausgabe
    }
    public boolean equipItem(Item item){
        for(int i=0; i<invContents.length; i++){
            if(invContents[i].name.equals(item.name)){
                removeItem(item);
                obtainItem(equippedItems[item.slot]);
                equippedItems[item.slot] = item;
                return true; // item wurde ausgerüstet, altes ist im inventar.
            }
        }
        return false; // item war nicht im inventar
    }
    public boolean removeItem(Item item)
    {
        for(int i=0; i< invContents.length; i++)
        {
            if(invContents[i].name.equals(item.name)){
                invContents[i] = null;
                return true; // item wurde entfernt
            }
        }
        return false; // TODO item nicht gefunden ausgabe?
    }*/
}