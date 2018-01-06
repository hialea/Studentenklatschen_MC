package de.hs_hannover.fholz.studentenklatschen.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Profil extends AppCompatActivity {

    /*List profilListe = new ArrayList<String>();
    ListAdapter profilAdapter = new ArrayAdapter<String>(profilListe);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
    }
}
