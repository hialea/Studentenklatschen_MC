package de.hs_hannover.fholz.studentenklatschen.Shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.Datamodel.Affix;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Inventory;
import de.hs_hannover.fholz.studentenklatschen.Datamodel.Item;
import de.hs_hannover.fholz.studentenklatschen.R;

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
                Random rand = new Random();
                int rndSlot = rand.nextInt(3);
                int itemlvl = 2;
                Affix affix = Affix.genAffix();
                Item item = new Item(rndSlot, itemlvl, affix);
                strengthView.setText(String.valueOf(item.attr[Attributes.Attr.STRENGTH]));
                defenseView.setText(String.valueOf(item.attr[Attributes.Attr.DEFENSE]));
                specialView.setText(String.valueOf(item.attr[Attributes.Attr.SPECIAL]));
                slotView.setText(Inventory.Slots.names[item.slot]);
                nameView.setText(item.name);
            }
        });
    }


}
