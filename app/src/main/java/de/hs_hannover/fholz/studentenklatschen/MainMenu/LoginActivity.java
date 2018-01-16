package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Profile;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Session;
import de.hs_hannover.fholz.studentenklatschen.QuestCat;
import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.databaseRef;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.mAuth;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.player;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerID;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

/*Bevor man Spielen kann, muss man sich anmelden. Für den Login sind Email und Passwort nötig.
* Der Login wird über Firebase geregelt*/

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION = 1;

    private EditText emailField;
    private EditText passwordField;
    private EditText profilName;
    private ImageView imageView;

    private FirebaseUser player;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        player = mAuth.getCurrentUser();

        emailField = (EditText) findViewById(R.id.login_field_email);
        passwordField = (EditText) findViewById(R.id.login_field_password);
        profilName = (EditText) findViewById(R.id.login_profil_field);

        findViewById(R.id.login_signin_button).setOnClickListener(this);
        findViewById(R.id.login_create_button).setOnClickListener(this);
        findViewById(R.id.login_complete_button).setOnClickListener(this);
        findViewById(R.id.login_camera_button).setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.profil_picture);

        View login = findViewById(R.id.login_layout);
        login.setVisibility(View.VISIBLE);

        View create = findViewById(R.id.profil_create_layout);
        create.setVisibility(View.GONE);

    }

    //Wenn man schon angemeldet ist, wird man zum Profil geleitet
    @Override
    public void onStart() {
        super.onStart();
        if(player!=null){
            Intent I = new Intent(LoginActivity.this, CharacterProfile.class);
            startActivity(I);
        }
    }

    //Erstellt einen Account und speichert ihn in der Datenbank.
    private void createAccount(String email, String password) {
        if (!validateForm()) {
            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Im Anschluss wird noch das Profil mit Bild und Namen erstellt. Die Kamera-Berechtigung wird erfragt
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        playerRef.setValue(new Profile(playerID));

                        View login = findViewById(R.id.login_layout);
                        login.setVisibility(View.GONE);

                        View create = findViewById(R.id.profil_create_layout);
                        create.setVisibility(View.VISIBLE);

                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION);
                        }
                    }
                }
            });
    }

    //Die App handelt basierend auf der gegebenen Kamera-Berechtigung
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(PERMISSION == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                findViewById(R.id.login_camera_button).setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                findViewById(R.id.login_camera_button).setVisibility(View.GONE);
            }
        }
    }

    //es wird geprüft ob alle Felder richtig aufgefüllt sind
    private boolean validateForm() {
        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            valid = false;
        }
        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            valid = false;
        }
        return valid;
    }

    //bei vorhandenem Account und erfolgreichem Login wird man zum Profil geleitet
    private void signIn(String email, String password) {
        if (!validateForm()) {
            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent I = new Intent(LoginActivity.this, CharacterProfile.class);
                        startActivity(I);
                    }
                }
            });
    }

    //Das geschossene Bild wird in die Datenbank geladen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        playerRef.child("image").setValue(bitmap);
    }

    //Nach Erfolgreicher Account-Erstellung wird man zum Fragenkatalog geleitet
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login_create_button) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.login_signin_button) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.login_camera_button) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        } else if (i == R.id.login_complete_button) {
            if(!TextUtils.isEmpty(profilName.getText().toString())){
                playerRef.child("name").setValue(profilName.getText().toString());
                Intent I = new Intent(LoginActivity.this, QuestCat.class);
                startActivity(I);
                this.onStop();
            }
        }
    }

}
