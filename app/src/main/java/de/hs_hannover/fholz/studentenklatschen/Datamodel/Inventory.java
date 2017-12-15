package de.hs_hannover.fholz.studentenklatschen.Datamodel;


public class Inventory {
    final int INV_SIZE = 10;
    public static class Slots{
        public static final int
            NUM_SLOTS = 4,
            HAND = 0,
            TOP = 1,
            BOOTS = 2,
            PANTS = 3;

        public static final String[] names =
        {
            "Laptop",
            "T-Shirt",
            "Schuhe",
            "Hose"
        };
    }
    int cur;
    Item[] contents,
            equipped;
    public Inventory(){
        cur = 0;
        equipped = new Item[Slots.NUM_SLOTS];
        contents = new Item[INV_SIZE];
    }
    public boolean obtainItem(Item item){
        for(int i=0; i < contents.length; i++)
        {
            if(contents[i]==null) {
                contents[i] = item;
                return true;
            }
        }
        return false; // TODO inventar voll ausgabe
    }
    public boolean equipItem(Item item){
        for(int i=0; i<contents.length; i++){
            if(contents[i].name.equals(item.name)){
                removeItem(item);
                obtainItem(equipped[item.slot]);
                equipped[item.slot] = item;
                return true; // item wurde ausgerüstet, altes ist im inventar.
            }
        }
        return false; // item war nicht im inventar
    }
    public boolean removeItem(Item item)
    {
        for(int i=0; i< contents.length; i++)
        {
            if(contents[i].name.equals(item.name)){
                contents[i] = null;
                return true; // item wurde entfernt
            }
        }
        return false; // TODO item nicht gefunden ausgabe?
    }
}