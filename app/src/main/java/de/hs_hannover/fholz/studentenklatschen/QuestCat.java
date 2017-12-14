package de.hs_hannover.fholz.studentenklatschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class QuestCat extends AppCompatActivity {

    private TextView Fragefeld;
    private TextView klickcount;
    private Button weiterFertig;
    private SeekBar bar;
    private int Klick = 1;
    private RadioGroup rG;
    private RadioButton sN;
    private RadioButton uF;
    private RadioButton tP;
    private RadioButton sT;
    int countSn = 0;
    int countUf = 0;
    int countTp = 0;
    int countSt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_cat);

        Fragefeld = (TextView) findViewById(R.id.Fragefeld);
        klickcount = (TextView) findViewById(R.id.Klicks);
        weiterFertig = (Button) findViewById(R.id.button);
        bar = (SeekBar) findViewById(R.id.Regler);
        rG = (RadioGroup) findViewById(R.id.radioGroup);
        sN = (RadioButton) findViewById(R.id.rBsn);
        uF = (RadioButton) findViewById(R.id.rBuf);
        tP = (RadioButton) findViewById(R.id.rBtp);
        sT = (RadioButton) findViewById(R.id.rBst);

        bar.setVisibility(View.INVISIBLE);
        Fragefeld.setText(R.string.frage1);
        weiterFertig.setText(R.string.weiter);
        sN.setText(R.string.aw1sn);
        uF.setText(R.string.aw1uf);
        tP.setText(R.string.aw1tp);
        sT.setText(R.string.aw1st);

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
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 2:
                        Fragefeld.setText(R.string.frage3);
                        sN.setText(R.string.aw3sn);
                        uF.setText(R.string.aw3uf);
                        tP.setText(R.string.aw3tp);
                        sT.setText(R.string.aw3st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 3:
                        bar.setVisibility(v.VISIBLE);
                        rG.setVisibility(v.INVISIBLE);
                        Fragefeld.setText(R.string.schieberegler1);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 4:
                        rG.setVisibility(v.VISIBLE);
                        bar.setVisibility(v.INVISIBLE);
                        Fragefeld.setText(R.string.frage4);
                        sN.setText(R.string.aw4sn);
                        uF.setText(R.string.aw4uf);
                        tP.setText(R.string.aw4tp);
                        sT.setText(R.string.aw4st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        /*int value = bar.;
                        klickcount.setText(value);*/
                        break;

                    case 5:
                        Fragefeld.setText(R.string.frage5);
                        sN.setText(R.string.aw5sn);
                        uF.setText(R.string.aw5uf);
                        tP.setText(R.string.aw5tp);
                        sT.setText(R.string.aw5st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 6:
                        Fragefeld.setText(R.string.frage6);
                        sN.setText(R.string.aw6sn);
                        uF.setText(R.string.aw6uf);
                        tP.setText(R.string.aw6tp);
                        sT.setText(R.string.aw6st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 7:
                        Fragefeld.setText(R.string.frage7);
                        sN.setText(R.string.aw7sn);
                        uF.setText(R.string.aw7uf);
                        tP.setText(R.string.aw7tp);
                        sT.setText(R.string.aw7st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 8:
                        Fragefeld.setText(R.string.frage8);
                        sN.setText(R.string.aw8sn);
                        uF.setText(R.string.aw8uf);
                        tP.setText(R.string.aw8tp);
                        sT.setText(R.string.aw8st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 9:
                        Fragefeld.setText(R.string.frage9);
                        sN.setText(R.string.aw9sn);
                        uF.setText(R.string.aw9uf);
                        tP.setText(R.string.aw9tp);
                        sT.setText(R.string.aw9st);
                        Klick++;
                        weiterFertig.setText(R.string.fertig);
                        klickcount.setText(countSn + " " + countUf + " " + countTp + " " + countSt);
                        break;

                    case 10:
                        finish();
                    default:
                        finish();
                }

                rG.clearCheck();
            }
        });
    }
}