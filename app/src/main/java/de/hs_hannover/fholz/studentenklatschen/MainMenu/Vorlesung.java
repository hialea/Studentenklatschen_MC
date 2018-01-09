package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import de.hs_hannover.fholz.studentenklatschen.R;

public class Vorlesung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorlesung);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Vorlesung.this, Profil.class);
                        startActivity(intent1);
                        //Toast.makeText(Hauptmenue.this, "Action Profil Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_kampf:
                        Intent intent2 = new Intent (Vorlesung.this, Kampf.class);
                        startActivity(intent2);
                        //Toast.makeText(Hauptmenue.this, "Action Kampf Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_reise:
                        Intent intent3 = new Intent (Vorlesung.this, Vorlesung.class);
                        startActivity(intent3);
                        //Toast.makeText(Hauptmenue.this, "Action Reise Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}
