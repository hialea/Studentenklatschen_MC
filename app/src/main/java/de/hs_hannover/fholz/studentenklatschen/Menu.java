package de.hs_hannover.fholz.studentenklatschen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

public class Menu extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    Intent I = new Intent(Menu.this, Travel.class);
                    startActivity(I);
                    return true;
                case R.id.navigation_fight:
                    mTextMessage.setText(R.string.title_fight);
                    return true;
                case R.id.navigation_travel:
                    mTextMessage.setText(R.string.title_travel);
                    return true;
                case R.id.navigation_shop:
                    mTextMessage.setText(R.string.title_shop);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
