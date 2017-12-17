package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.media.Image;

public class Profile {
//TODO vlt enums die in arrays als index benutzt werden mit enummap ersetzen
    public String id;
    public Character character;
    public String name;
    public Image image;

    public Profile(){

    }
    public Profile(String id){
        this.id=id;
        this.character=null;
        this.name=null;
        this.image=null;
    }

}
