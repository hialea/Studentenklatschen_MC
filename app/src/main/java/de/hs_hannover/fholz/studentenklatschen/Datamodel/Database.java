package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Franzi on 08.01.2018.
 */

public class Database {
    public static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static final FirebaseUser player = mAuth.getCurrentUser();
    public static final String playerID = player.getUid();
    public static final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    public static final DatabaseReference playerbaseRef = FirebaseDatabase.getInstance().getReference().child("Playerbase");
    public static final DatabaseReference playerRef = FirebaseDatabase.getInstance().getReference().child("Playerbase").child(playerID);
    public static final DatabaseReference charRef = FirebaseDatabase.getInstance().getReference().child("Playerbase").child(playerID).child("character");
    public static final DatabaseReference sessionRef = FirebaseDatabase.getInstance().getReference().child("Session");

}
