package de.hs_hannover.fholz.studentenklatschen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Character;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.LoginActivity;
import de.hs_hannover.fholz.studentenklatschen.MainMenu.Profil;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Database.*;


public class QuestCat extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseUser user;
    public DatabaseReference loginRef;
    private TextView Fragefeld, max, min;
    private Button weiterFertig;
    private SeekBar bar;
    private int Klick = 1;
    private RadioGroup rG;
    private RadioButton sN, uF, tP, sT;
    int countSn = 0;
    int countUf = 0;
    int countTp = 0;
    int countSt = 0;
    int sliderProg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_cat);

        Fragefeld = (TextView) findViewById(R.id.Fragefeld);
        max = (TextView) findViewById(R.id.Max);
        min = (TextView) findViewById(R.id.Min);
        weiterFertig = (Button) findViewById(R.id.button);
        bar = (SeekBar) findViewById(R.id.Regler);
        rG = (RadioGroup) findViewById(R.id.radioGroup);
        sN = (RadioButton) findViewById(R.id.rBsn);
        uF = (RadioButton) findViewById(R.id.rBuf);
        tP = (RadioButton) findViewById(R.id.rBtp);
        sT = (RadioButton) findViewById(R.id.rBst);

        bar.setVisibility(View.INVISIBLE);
        max.setVisibility(View.INVISIBLE);
        min.setVisibility(View.INVISIBLE);
        Fragefeld.setText(R.string.frage1);
        weiterFertig.setText(R.string.weiter);
        sN.setText(R.string.aw1sn);
        uF.setText(R.string.aw1uf);
        tP.setText(R.string.aw1tp);
        sT.setText(R.string.aw1st);

        bar.setMax(3);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sliderProg = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        weiterFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (rG.getCheckedRadioButtonId()) {
                    case R.id.rBsn:
                        countSn++;
                        break;
                    case R.id.rBuf:
                        countUf++;
                        break;
                    case R.id.rBtp:
                        countTp++;
                        break;
                    case R.id.rBst:
                        countSt++;
                        break;
                }

                switch (Klick) {
                    case 1:
                        Fragefeld.setText(R.string.frage2);
                        sN.setText(R.string.aw2sn);
                        uF.setText(R.string.aw2uf);
                        tP.setText(R.string.aw2tp);
                        sT.setText(R.string.aw2st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 2:
                        Fragefeld.setText(R.string.frage3);
                        sN.setText(R.string.aw3sn);
                        uF.setText(R.string.aw3uf);
                        tP.setText(R.string.aw3tp);
                        sT.setText(R.string.aw3st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 3:
                        bar.setVisibility(v.VISIBLE);
                        min.setVisibility(v.VISIBLE);
                        max.setVisibility(v.VISIBLE);
                        rG.setVisibility(v.INVISIBLE);
                        Fragefeld.setText(R.string.schieberegler1);
                        max.setText(R.string.srb);
                        min.setText(R.string.sra);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 4:
                        sliderProgress(sliderProg);
                        clearSlider();
                        bar.setVisibility(v.INVISIBLE);
                        min.setVisibility(v.INVISIBLE);
                        max.setVisibility(v.INVISIBLE);
                        rG.setVisibility(v.VISIBLE);
                        Fragefeld.setText(R.string.frage4);
                        sN.setText(R.string.aw4sn);
                        uF.setText(R.string.aw4uf);
                        tP.setText(R.string.aw4tp);
                        sT.setText(R.string.aw4st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 5:
                        Fragefeld.setText(R.string.frage5);
                        sN.setText(R.string.aw5sn);
                        uF.setText(R.string.aw5uf);
                        tP.setText(R.string.aw5tp);
                        sT.setText(R.string.aw5st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 6:
                        Fragefeld.setText(R.string.frage6);
                        sN.setText(R.string.aw6sn);
                        uF.setText(R.string.aw6uf);
                        tP.setText(R.string.aw6tp);
                        sT.setText(R.string.aw6st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 7:
                        bar.setVisibility(v.VISIBLE);
                        min.setVisibility(v.VISIBLE);
                        max.setVisibility(v.VISIBLE);
                        rG.setVisibility(v.INVISIBLE);
                        Fragefeld.setText(R.string.schieberegler2);
                        max.setText(R.string.srb);
                        min.setText(R.string.sra);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 8:
                        sliderProgress(sliderProg);
                        clearSlider();
                        bar.setVisibility(v.INVISIBLE);
                        min.setVisibility(v.INVISIBLE);
                        max.setVisibility(v.INVISIBLE);
                        rG.setVisibility(v.VISIBLE);
                        Fragefeld.setText(R.string.frage7);
                        sN.setText(R.string.aw7sn);
                        uF.setText(R.string.aw7uf);
                        tP.setText(R.string.aw7tp);
                        sT.setText(R.string.aw7st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 9:
                        Fragefeld.setText(R.string.frage8);
                        sN.setText(R.string.aw8sn);
                        uF.setText(R.string.aw8uf);
                        tP.setText(R.string.aw8tp);
                        sT.setText(R.string.aw8st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 10:
                        Fragefeld.setText(R.string.frage9);
                        sN.setText(R.string.aw9sn);
                        uF.setText(R.string.aw9uf);
                        tP.setText(R.string.aw9tp);
                        sT.setText(R.string.aw9st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 11:
                        bar.setVisibility(v.VISIBLE);
                        min.setVisibility(v.VISIBLE);
                        max.setVisibility(v.VISIBLE);
                        rG.setVisibility(v.INVISIBLE);
                        Fragefeld.setText(R.string.schieberegler3);
                        max.setText(R.string.srb);
                        min.setText(R.string.sra);
                        Klick++;
                        weiterFertig.setText(R.string.fertig);
                        break;

                    case 12:
                        sliderProgress(sliderProg);
                        bar.setVisibility(v.INVISIBLE);
                        min.setVisibility(v.INVISIBLE);
                        max.setVisibility(v.INVISIBLE);
                        if (countSn >= countSt) {
                            if (countSn >= countUf) {
                                if (countSn >= countTp) {
                                    Fragefeld.setText(R.string.eine + R.string.charaSn);
                                    charRef.setValue(new Character(Character.Role.SCHNARCHNASE));
                                } else {
                                    Fragefeld.setText(R.string.ein + R.string.charaTp);
                                    charRef.setValue(new Character(Character.Role.TOLLPATSCH));
                                }
                            } else {
                                Fragefeld.setText(R.string.ein + R.string.charaUf);
                                charRef.setValue(new Character(Character.Role.UEBERFLIEGER));
                            }
                        } else if (countSt >= countTp) {
                            if (countSt >= countUf) {
                                Fragefeld.setText(R.string.eine + R.string.charaSt);
                                charRef.setValue(new Character(Character.Role.SNAPCHAT_TUSSI));
                            } else {
                                Fragefeld.setText(R.string.ein + R.string.charaUf);
                                charRef.setValue(new Character(Character.Role.UEBERFLIEGER));
                            }
                        } else if (countTp >= countUf) {
                            Fragefeld.setText(R.string.ein + R.string.charaTp);
                            charRef.setValue(new Character(Character.Role.TOLLPATSCH));
                        } else {
                            Fragefeld.setText(R.string.ein + R.string.charaUf);
                            charRef.setValue(new Character(Character.Role.UEBERFLIEGER));
                        }
                        weiterFertig.setText(R.string.fertig);
                        Klick++;
                        break;

                    case 13:
                        Intent I = new Intent(QuestCat.this, Profil.class);
                        startActivity(I);

                    default:
                        Intent I = new Intent(QuestCat.this, Profil.class);
                        startActivity(I);
                }

                rG.clearCheck();
            }
        });
    }

    public void sliderProgress (int i) {
        switch (i) {
            case 0:
                countSn ++;
                break;
            case 1:
                countTp ++;
                break;
            case 2:
                countSt ++;
                break;
            case 3:
                countUf ++;
                break;
        }
    }

    public void clearSlider() {
        bar.setProgress(0);
        sliderProg = 0;
    }
}