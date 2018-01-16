package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.util.ArrayList;

import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;

/**
 * Created by Franzi on 10.01.2018.
 */

public class Session {

    public ArrayList<Room> rooms;

    public static class Room{

        public static int number =  1;
        public static Profil player1;
        public static Profil player2;
        public static int name;

        public Room(){
            this.name = number;
            number++;
        }
    }

    public Session(){
        this.rooms = new ArrayList<>();
        rooms.add(new Room());
    }
}
