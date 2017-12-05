package de.hs_hannover.fholz.studentenklatschen.Datamodel;

class Affix {
    int[] factors;
    String name;
    Affix(String name, int str, int def, int spc){
        factors[Attributes.Attr.STRENGTH] = str;
        factors[Attributes.Attr.DEFENSE] = def;
        factors[Attributes.Attr.SPECIAL] = spc;
        this.name = name;
    }
    //TODO affixe hardcoden
}
