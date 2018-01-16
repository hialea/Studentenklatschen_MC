package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.graphics.Bitmap;
import android.media.Image;

/*Das Profil enthält alle Daten-Strukturen, die für das Spiel benötigt werden*/

public class Profile {

    public String id;
    public Character character;
    public String name;
    public Bitmap image;
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
