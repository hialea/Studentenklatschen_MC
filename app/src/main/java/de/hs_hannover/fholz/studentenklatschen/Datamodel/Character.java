package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.attributeName;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.slotName;

/*Mit dem Charakter bewegt man sich durch die Welt von Studiklatsche.
Man kann Erfahrungspunkte sammeln und Level aufsteigen. Die gesammelten Items werden im Inventar abgelegt.*/

public class Character {

    //Die vier spielbaren Klassen
    public enum Role{
        SNAPCHAT_TUSSI,
        UEBERFLIEGER,
        SCHNARCHNASE,
        TOLLPATSCH
    }

    public Attributes attributes;
    public Role role;
    public Inventory inventory;
    public String name;
    public int level;
    public int exp;
    public int lifepoints;
    public History history;

    public Character(Role role) {
        this.name = null;
        this.attributes = new Attributes(role);
        this.role = role;
        this.inventory = new Inventory();
        this.level = 1;
        this.exp = 0;
        this.lifepoints = 100+(level*10);
        this.history = new History();
    }

    public Character() {

    }

    //Erfahrungspunkte und Levelaufstieg berechnen
    public boolean gainXP(int xp){
        exp+=xp;
        int xpreq = xpRequired(level);
        if(exp>xpreq){
            exp-=xpreq;
            level++;
            return true;
        }
        return false;
    }

    //benötigte Punkte bis zum Aufstieg
    private int xpRequired(int level){
        return (int)Math.pow(2, level);
    }

    //berechnet die neuen Attribute nach Anlegen eines Items
    public void updateAttributes(){
        for(int i=0; i<attributeName.length; i++) {
            int newAttr=0;
            for(int j=0; j<slotName.length; j++){
                if(inventory.equippedItems.get(slotName[j])!=null){
                    newAttr += inventory.equippedItems.get(slotName[j]).attributes.get(attributeName[i]);
                }
            }
            newAttr += attributes.charAttributes.get(attributeName[i]);
            attributes.allAttributes.put(attributeName[i], newAttr);
        }
    }
}