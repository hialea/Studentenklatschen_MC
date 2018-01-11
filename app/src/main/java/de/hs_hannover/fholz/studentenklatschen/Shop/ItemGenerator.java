package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Affix;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.*;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory.Slots.slotName;

public class ItemGenerator extends AppCompatActivity {

    private TextView strengthView;
    private TextView defenseView;
    private TextView specialView;
    private TextView slotView;
    private TextView nameView;
    private Button generateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_generator);

        strengthView = (TextView) findViewById(R.id.strength_view);
        defenseView = (TextView) findViewById(R.id.defense_view);
        specialView = (TextView) findViewById(R.id.special_view);
        slotView = (TextView) findViewById(R.id.slot_view);
        nameView = (TextView) findViewById(R.id.name_view);
        generateBtn = (Button) findViewById(R.id.randomize);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemlvl = 2;
                Item item = new Item(itemlvl);
                strengthView.setText(String.valueOf(item.attributes.get(attributeName[STRENGTH])));
                defenseView.setText(String.valueOf(item.attributes.get(attributeName[DEFENSE])));
                specialView.setText(String.valueOf(item.attributes.get(attributeName[SPECIAL])));
                slotView.setText(slotName[item.slot]);
                nameView.setText(item.name);
            }
        });
    }


}
