package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.media.Image;

public class Profile {
//TODO vlt enums die in arrays als index benutzt werden mit enummap ersetzen
    private String id;
    private Character character;
    private String name;
    private Image image;

    public Profile(String id, Character character, String name, Image image){
        this.id=id;
        this.character=character;
        this.name=name;
        this.image=image;
    }
    public Profile(String id, Character character, String name){
        this.id=id;
        this.character=character;
        this.name=name;
        this.image=null;
    }
    public Profile(String id){
        this.id=id;
        this.character=null;
        this.name=null;
        this.image=null;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setImage(Image image){
        this.image=image;
    }

    public void setCharacter(Character character){
        this.character=character;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Image getImage(){
        return image;
    }

    public Character getCharacter(){
        return character;
    }

}
