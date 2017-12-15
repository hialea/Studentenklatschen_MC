package de.hs_hannover.fholz.studentenklatschen.Datamodel;

public class Item {
    public String name;
    int itemLVL;
    public int slot;
    public int[] attr = new int[3];
    Affix affix;
    public Item(int slot, int itemlvl, Affix affix) // use finals from Inventory.Slots for slot; create static selection of affix objects
    {
        this.affix = affix;
        itemLVL = itemlvl;
        attr[Attributes.Attr.STRENGTH] = genStat(itemLVL, affix, Attributes.Attr.STRENGTH);
        attr[Attributes.Attr.DEFENSE] = genStat(itemLVL, affix, Attributes.Attr.DEFENSE);
        attr[Attributes.Attr.SPECIAL] = genStat(itemLVL, affix, Attributes.Attr.SPECIAL);
        this.slot = slot;
        name = genName(Inventory.Slots.names[slot], affix);

    }
    private int genStat(int lvl, Affix affix, int atr){
        return (int)(lvl * Math.random() * affix.factors[atr]);
    }
    private String genName(String slot, Affix affix){
        return slot + " " + affix.name; // TODO
    }
}
