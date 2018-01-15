package de.hs_hannover.fholz.studentenklatschen.MainMenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Profile;
import de.hs_hannover.fholz.studentenklatschen.QuestCat;
import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerID;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.playerRef;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailField;
    private EditText passwordField;

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

        findViewById(R.id.login_signin_button).setOnClickListener(this);
        findViewById(R.id.login_create_button).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(player!=null){

            Intent I = new Intent(LoginActivity.this, Profil.class);
            startActivity(I);
        }
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        playerRef.setValue(new Profile(playerID));
                        Intent I = new Intent(LoginActivity.this, QuestCat.class);
                        startActivity(I);
                    }
                }
            });
    }

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
                        Intent I = new Intent(LoginActivity.this, Profil.class);
                        startActivity(I);
                    }
                }
            });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login_create_button) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.login_signin_button) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        }
    }
}
