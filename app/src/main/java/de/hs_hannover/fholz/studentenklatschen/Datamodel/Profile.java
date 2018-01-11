package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.media.Image;

public class Profile {

    public String id;
    public Character character;
    public String name;
    public Image image;
    public History history;

    public Profile(){

    }

    public Profile(String id){
        this.id=id;
        this.character=null;
        this.name=null;
        this.image=null;
        this.history=null;
    }

}
