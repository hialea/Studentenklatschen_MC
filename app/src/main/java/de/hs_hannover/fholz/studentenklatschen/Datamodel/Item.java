package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.util.HashMap;
import java.util.Map;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.*;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.slotName;

public class Item {
    public String name;
    int itemLVL;
    int value;
    public int slot;
    public Map<String, Integer> attributes;
    public Affix affix;

    public Item(int slot, int itemlvl, Affix affix) // use finals from Inventory.Slots for slot; create static selection of affix objects
    {
        this.affix = affix;
        this.slot = slot;
        this.itemLVL = itemlvl;
        this.value = itemlvl*300;
        attributes = new HashMap<>();
        attributes.put(attributeName[STRENGTH], genStat(itemlvl, affix, STRENGTH));
        attributes.put(attributeName[DEFENSE], genStat(itemlvl, affix, DEFENSE));
        attributes.put(attributeName[SPECIAL], genStat(itemlvl, affix, SPECIAL));

        name = genName(slotName[slot], affix);

    }
    private int genStat(int lvl, Affix affix, int atr){
        return (int)(lvl * Math.random() * affix.factors.get(attributeName[atr]));
    }
    private String genName(String slot, Affix affix){
        return slot + " " + affix.name; // TODO
    }
}
