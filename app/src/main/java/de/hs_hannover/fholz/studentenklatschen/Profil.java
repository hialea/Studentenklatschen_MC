package de.hs_hannover.fholz.studentenklatschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Profil extends AppCompatActivity {

    List profilListe = new ArrayList<String>();
    ListAdapter profilAdapter = new ArrayAdapter<String>(profilListe);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        profilListe.add("user123");
        profilListe.add("Schnarchnase");
        profilListe.add("Level 1");
        profilListe.add("23 Punkte");


    }
}
