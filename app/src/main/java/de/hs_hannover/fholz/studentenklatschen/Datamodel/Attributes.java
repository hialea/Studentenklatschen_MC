package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.Attr.*;

public class Attributes {

    public class Attr{
        public static final int NUMBER_ATTRS = 3,
        STRENGTH = 0,
        DEFENSE = 1,
        SPECIAL = 2;
    }

    int[] attributes;

    public Attributes(Character.Role role) {
        attributes= new int[NUMBER_ATTRS];
        switch(role){ // should have equal total stats
            case TOLLPATSCH:
                attributes[STRENGTH] = 0;
                attributes[DEFENSE] = 1;
                attributes[SPECIAL] = 0;
                break;
            case SCHNARCHNASE:
                attributes[STRENGTH] = 1;
                attributes[DEFENSE] = 1;
                attributes[SPECIAL] = 0;
                break;
            case SNAPCHAT_TUSSI:
                attributes[STRENGTH] = 0;
                attributes[DEFENSE] = 1;
                attributes[SPECIAL] = 1;
                break;
            case UEBERFLIEGER:
                attributes[STRENGTH] = 1;
                attributes[DEFENSE] = 0;
                attributes[SPECIAL] = 1;
                break;
            default:
                attributes[STRENGTH] = 0;
                attributes[DEFENSE] = 0;
                attributes[SPECIAL] = 0;
                break;

        }
    }

    void increaseAtr(int which){
        attributes[which]++;
    }
}
