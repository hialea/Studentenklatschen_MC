package de.hs_hannover.fholz.studentenklatschen;

import android.media.Image;

/**
 * Created by efw-kz4-u1 on 23.11.17.
 */

public class Profil {

    private int id;
    private Character character;
    private String name;
    private Image image;

    public Profil(int id, Character character, String name, Image image){
        this.id=id;
        this.character=character;
        this.name=name;
        this.image=image;
    }

    public Profil(){

    }

    public void setId(int id){
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

    public int getId(){
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
