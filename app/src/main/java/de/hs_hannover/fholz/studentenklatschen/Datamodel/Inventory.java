package de.hs_hannover.fholz.studentenklatschen.Datamodel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.*;

/*Im Inventar werden die gesammelten Items des Charakters gespeichert. Es hat eine feste Größe. Aus dem Iventar können die Items ausgerüstet werden*/

public class Inventory {

    //Es gibt vier mögliche Ausrüstungs-Plätze
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
    public int klatschis;

    public Inventory(){
        klatschis = INV_SIZE;
        slotsTaken = 0;
        equippedItems = new HashMap<String, Item>(){
            {
                put(slotName[LAPTOP], null);
                put(slotName[SHIRT], null);
                put(slotName[BOOTS], null);
                put(slotName[PANTS], null);
            }
        };
        invContents = new ArrayList<>();
    }

    public boolean obtainItem(Item item){
        if(invContents.size()<10) {
            invContents.add(item);
            return true;
        }
        return false;
    }

    public boolean equipItem(Item toEquip){
        for(Item content : invContents){
            if(toEquip.name.equals(content.name)){
                removeItem(toEquip);
                obtainItem(equippedItems.get(slotName[toEquip.slot]));
                equippedItems.put(slotName[toEquip.slot], toEquip);
                return true;
            }
        }
        return false;

    }

    public boolean removeItem(Item toRemove){

        for(Item content : invContents){
            if(toRemove.name.equals(content.name)){
                invContents.remove(content);
                return true;
            }
        }
        return false;
    }

}