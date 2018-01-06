package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.util.HashMap;
import java.util.Map;


public class Attributes {

    public static final int
        STRENGTH=0,
        DEFENSE=1,
        SPECIAL=2;

    public static final String[] attributeName = {
        "STRENGTH",
        "DEFENSE",
        "SPECIAL"
    };

    public Map<String, Integer> charAttributes;
    public Map<String, Integer> allAttributes;

    public Attributes(Character.Role role) {
        charAttributes = new HashMap<>();
        allAttributes = new HashMap<>();

        switch(role){ // should have equal total stats
            case TOLLPATSCH:
                charAttributes.put(attributeName[STRENGTH], 1);
                charAttributes.put(attributeName[DEFENSE], 0);
                charAttributes.put(attributeName[SPECIAL], 0);

                allAttributes.put(attributeName[STRENGTH], 1);
                allAttributes.put(attributeName[DEFENSE], 0);
                allAttributes.put(attributeName[SPECIAL], 0);
                break;
            case SCHNARCHNASE:
                charAttributes.put(attributeName[STRENGTH], 1);
                charAttributes.put(attributeName[DEFENSE], 1);
                charAttributes.put(attributeName[SPECIAL], 0);

                allAttributes.put(attributeName[STRENGTH], 1);
                allAttributes.put(attributeName[DEFENSE], 1);
                allAttributes.put(attributeName[SPECIAL], 0);
                break;
            case SNAPCHAT_TUSSI:
                charAttributes.put(attributeName[STRENGTH], 1);
                charAttributes.put(attributeName[DEFENSE], 0);
                charAttributes.put(attributeName[SPECIAL], 1);

                allAttributes.put(attributeName[STRENGTH], 1);
                allAttributes.put(attributeName[DEFENSE], 0);
                allAttributes.put(attributeName[SPECIAL], 1);
                break;
            case UEBERFLIEGER:
                charAttributes.put(attributeName[STRENGTH], 0);
                charAttributes.put(attributeName[DEFENSE], 1);
                charAttributes.put(attributeName[SPECIAL], 1);

                allAttributes.put(attributeName[STRENGTH], 0);
                allAttributes.put(attributeName[DEFENSE], 1);
                allAttributes.put(attributeName[SPECIAL], 1);
                break;
            default:
                charAttributes.put(attributeName[STRENGTH], 0);
                charAttributes.put(attributeName[DEFENSE], 0);
                charAttributes.put(attributeName[SPECIAL], 0);

                allAttributes.put(attributeName[STRENGTH], 0);
                allAttributes.put(attributeName[DEFENSE], 0);
                allAttributes.put(attributeName[SPECIAL], 0);
                break;

        }
    }

    void increaseAttribute(int attribute){
        int attr;
        switch(attribute){
            case STRENGTH:
                attr = charAttributes.get(attributeName[STRENGTH]);
                charAttributes.put(attributeName[STRENGTH], ++attr);
            case DEFENSE:
                attr = charAttributes.get(attributeName[DEFENSE]);
                charAttributes.put(attributeName[DEFENSE], ++attr);
            case SPECIAL:
                attr = charAttributes.get(attributeName[SPECIAL]);
                charAttributes.put(attributeName[SPECIAL], ++attr);
        }

    }
}
