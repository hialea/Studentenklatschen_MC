package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.attributeName;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.slotName;

public class Character {

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
        this.lifepoints = 1000;
        this.history = new History();
    }

    public Character() {

    }

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

    private int xpRequired(int level){
        return (int)Math.pow(2, level);
    }

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