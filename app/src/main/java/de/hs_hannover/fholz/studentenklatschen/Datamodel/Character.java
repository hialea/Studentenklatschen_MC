package de.hs_hannover.fholz.studentenklatschen.Datamodel;

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

    public Character(Role role) {
        this.name = null;
        this.attributes = new Attributes(role);
        this.role = role;
        this.inventory = new Inventory();
        this.level = 1;
        this.exp = 0;
    }

    public Character() {

    }
/*    public void gainXP(int xp){
        exp+=xp;
        int xpreq = XPreq(level);
        if(exp>=xpreq){
            exp-=xpreq;
            level++;
            // show dialogue to choose a stat to increase
            int atr= Attributes.Attr.STRENGTH *//*dialogue()*//*;
            switch(atr){
                case Attributes.Attr.STRENGTH:
                    charAttributes.increaseAtr(Attributes.Attr.STRENGTH);
                    break;
                case Attributes.Attr.DEFENSE:
                    charAttributes.increaseAtr(Attributes.Attr.DEFENSE);
                    break;
                case Attributes.Attr.SPECIAL:
                    charAttributes.increaseAtr(Attributes.Attr.SPECIAL);
                    break;
                default:
                    break;
            }
        }
    }*/
    private int XPreq(int level){
        return (int)Math.pow(2, level);
    }
}