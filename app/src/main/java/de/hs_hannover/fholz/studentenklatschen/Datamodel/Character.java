package de.hs_hannover.fholz.studentenklatschen.Datamodel;


public class Character {

    public enum Role{
        SNAPCHAT_TUSSI,
        UEBERFLIEGER,
        SCHNARCHNASE,
        PECHVOGEL
    }

    Attributes attributes;
    Role role;
    Inventory inventory;
    String name;
    int level;
    int exp;

    public Character(String name, Role role) {
        this.name = name;
        this.attributes = new Attributes(role);
        this.role = role;
        this.inventory = new Inventory();
        this.level = 1;
        this.exp = 0;
    }
    public void gainXP(int xp){
        exp+=xp;
        int xpreq = XPreq(level);
        if(exp>=xpreq){
            exp-=xpreq;
            level++;
            // show dialogue to choose a stat to increase
            int atr= Attributes.Attr.STRENGTH /*dialogue()*/;
            switch(atr){
                case Attributes.Attr.STRENGTH:
                    attributes.increaseAtr(Attributes.Attr.STRENGTH);
                    break;
                case Attributes.Attr.DEFENSE:
                    attributes.increaseAtr(Attributes.Attr.DEFENSE);
                    break;
                case Attributes.Attr.SPECIAL:
                    attributes.increaseAtr(Attributes.Attr.SPECIAL);
                    break;
                default:
                    break;
            }
        }
    }
    private int XPreq(int level){
        return (int)Math.pow(2, level);
    }
}