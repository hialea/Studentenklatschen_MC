package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.hs_hannover.fholz.studentenklatschen.R;
import de.hs_hannover.fholz.studentenklatschen.Shop.Shop;
import de.hs_hannover.fholz.studentenklatschen.Travel.Travel;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.mAuth;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

public class Profil extends AppCompatActivity {

    public ImageView imageViewP;
    public Button signout;
    public Button cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        imageViewP = (ImageView) findViewById(R.id.imageView);
        signout = (Button) findViewById(R.id.profil_signout_button);
        cam = (Button) findViewById(R.id.camera_button);

        Button btnabout = (Button)findViewById(R.id.about);
        btnabout.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Profil.this, About.class);
                        startActivity(I);
                    }
                }
        );

        Button btncredits = (Button)findViewById(R.id.credits);
        btncredits.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent I = new Intent(Profil.this, About.class);
                        startActivity(I);
                    }
                }
        );

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent (Profil.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /*playerRef.child("image").addListenerForSingleValueEvent(new ValueEventListener() {
            Bitmap image;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    image = snapshot.getValue(Bitmap.class);
                }
                //Bitmap bitmap = (Bitmap) dataSnapshot.getValue();
                imageViewP.setImageBitmap(image);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_profil2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_profil:
                        Intent intent1 = new Intent (Profil.this, CharacterProfile.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_fight:
                        Intent intent2 = new Intent (Profil.this, GeneratedEnemy.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_travel:
                        Intent intent3 = new Intent (Profil.this, Travel.class);
                        startActivity(intent3);
                        break;

                    case R.id.action_shop:
                        Intent intent4 = new Intent (Profil.this, Shop.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageViewP.setImageBitmap(bitmap);
        playerRef.child("image").setValue(bitmap);
    }
}
