package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }
}
