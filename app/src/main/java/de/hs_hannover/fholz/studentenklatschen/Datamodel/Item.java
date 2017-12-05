package de.hs_hannover.fholz.studentenklatschen.Datamodel;

class Item {
    String name;
    int itemLVL;
    int slot;
    int[] attr;
    Affix affix;
    Item(int slot, int itmlvl, Affix affix) // use finals from Inventory.Slots for slot; create static selection of affix objects
    {
        this.affix = affix;
        itemLVL = itmlvl;
        attr[Attributes.Attr.STRENGTH] = genStat(itemLVL, affix, Attributes.Attr.STRENGTH);
        attr[Attributes.Attr.DEFENSE] = genStat(itemLVL, affix, Attributes.Attr.DEFENSE);
        attr[Attributes.Attr.SPECIAL] = genStat(itemLVL, affix, Attributes.Attr.SPECIAL);
        this.slot = slot;
        name = genName(Inventory.Slots.names[slot], affix);

    }
    private int genStat(int lvl, Affix affix, int atr) {
        return (int)(lvl * Math.random() * affix.factors[atr]);
    }
    private String genName(String slot, Affix affix){
        return slot + " " + affix.name; // TODO
    }
}
