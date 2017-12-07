package de.hs_hannover.fholz.studentenklatschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestCat extends AppCompatActivity {

    private TextView Fragefeld;
    private TextView klickcount;
    private Button weiterFertig;
    private int Klick = 1;
    private RadioButton sN;
    private RadioButton uF;
    private RadioButton tP;
    private RadioButton sT;
    private int countSn = 0;
    private int countUf = 0;
    private int countTp = 0;
    private int countSt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_cat);

        Fragefeld = (TextView) findViewById(R.id.Fragefeld);
        klickcount = (TextView) findViewById(R.id.Klicks);
        weiterFertig = (Button) findViewById(R.id.button);
        sN = (RadioButton) findViewById(R.id.rBsn);
        uF = (RadioButton) findViewById(R.id.rBuf);
        tP = (RadioButton) findViewById(R.id.rBtp);
        sT = (RadioButton) findViewById(R.id.rBst);

        Fragefeld.setText(R.string.frage1);
        weiterFertig.setText(R.string.weiter);
        sN.setText(R.string.aw1sn);
        uF.setText(R.string.aw1uf);
        tP.setText(R.string.aw1tp);
        sT.setText(R.string.aw1st);

        weiterFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*boolean checked = ((RadioButton)v).isChecked();

                switch (v.getId()) {
                    case R.id.rBsn:
                        if (checked) {
                            countSn++;
                        }
                        break;
                    case R.id.rBuf:
                        if (checked) {
                            countUf++;
                        }
                        break;
                    case R.id.rBtp:
                        if (checked) {
                            countTp++;
                        }
                        break;
                    case R.id.rBst:
                        if (checked) {
                            countSt++;
                        }
                        break;
                }*/

                //klickcount.setText("Hallo");

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
                        Fragefeld.setText(R.string.frage4);
                        sN.setText(R.string.aw4sn);
                        uF.setText(R.string.aw4uf);
                        tP.setText(R.string.aw4tp);
                        sT.setText(R.string.aw4st);
                        Klick++;
                        weiterFertig.setText(R.string.weiter);
                        break;

                    case 4:
                        Fragefeld.setText(R.string.frage5);
                        sN.setText(R.string.aw5sn);
                        uF.setText(R.string.aw5uf);
                        tP.setText(R.string.aw5tp);
                        sT.setText(R.string.aw5st);
                        Klick++;
                        weiterFertig.setText(R.string.fertig);
                        break;
                    case 5:
                        finish();
                }
            }
        });

    }

   /* public void clickedRB(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.rBsn:
                if (checked)
                    countSn++;
                break;
            case R.id.rBuf:
                if (checked)
                    countUf++;
                break;
            case R.id.rBtp:
                if (checked)
                    countTp++;
                break;
            case R.id.rBst:
                if (checked)
                    countSt++;
                break;
        }
    }*/

}