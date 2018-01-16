package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.*;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.slotName;

//Konstruktor für die Erstellung von Items. Items kann der Spieler im Shop kaufen und sich ausrüsten. Die Eigenschaften werden mit Hilfe von Affix generiert.

public class Item {

    public String name;
    int itemLVL;
    int value;
    public int slot;
    public Map<String, Integer> attributes;
    public Affix affix;

    public Item(int itemlvl) {
        Random rand = new Random();
        this.slot = rand.nextInt(4);
        this.affix = Affix.genAffix();
        this.itemLVL = itemlvl;
        this.value = itemlvl*500;
        attributes = new HashMap<>();
        attributes.put(attributeName[STRENGTH], genStat(itemlvl, affix, STRENGTH));
        attributes.put(attributeName[DEFENSE], genStat(itemlvl, affix, DEFENSE));
        attributes.put(attributeName[SPECIAL], genStat(itemlvl, affix, SPECIAL));
        name = genName(slotName[slot], affix);
    }

    //generiert mit Hilfe eines Affix die Werte des Items basierend auf dem Charakter-Level
    private int genStat(int lvl, Affix affix, int atr){
        return (int)(lvl * Math.random() * affix.factors.get(attributeName[atr]));
    }

    //Der Name des Items wird aus dem Slot und dem Affix generiert
    private String genName(String slot, Affix affix){
        return slot + " " + affix.name;
    }

}
