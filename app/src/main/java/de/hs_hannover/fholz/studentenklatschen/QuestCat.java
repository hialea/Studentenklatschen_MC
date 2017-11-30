package de.hs_hannover.fholz.studentenklatschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestCat extends AppCompatActivity {

    private TextView Fragefeld;
    private Button weiterFertig;
    private int Klick;
    private RadioButton sN;
    private RadioButton uF;
    private RadioButton tP;
    private RadioButton sT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_cat);

        Fragefeld = (TextView) findViewById(R.id.Fragefeld);
        weiterFertig = (Button) findViewById(R.id.button);
        sN = (RadioButton) findViewById(R.id.rBsn);
        uF = (RadioButton) findViewById(R.id.rBuf);
        tP = (RadioButton) findViewById(R.id.rBtp);
        sT = (RadioButton) findViewById(R.id.rBst);
        Klick = 1;

        Fragefeld.setText(R.string.frage1);
        weiterFertig.setText(R.string.weiter);
        sN.setText(R.string.aw1sn);
        uF.setText(R.string.aw1uf);
        tP.setText(R.string.aw1tp);
        sT.setText(R.string.aw1st);

        weiterFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickedRB(v);

                switch (Klick) {
                    case 1:
                        Fragefeld.setText(R.string.frage2);
                        sN.setText(R.string.aw2sn);
                        uF.setText(R.string.aw2uf);
                        tP.setText(R.string.aw2tp);
                        sT.setText(R.string.aw2st);
                        Klick++;
                        weiterFertig.setText(R.string.fertig);
                        break;
                    case 2:
                        finish();
                        break;
                }
            }
        });

    }

    public int clickedRB(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rBsn:
                if (checked)
                    return 1;
                break;
            case R.id.rBuf:
                if (checked)
                    return 2;
                break;
            case R.id.rBtp:
                if (checked)
                    return 3;
                break;
            case R.id.rBst:
                if (checked)
                    return 4;
                break;
        }
        return 0;
    }
}
