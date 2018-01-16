package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hs_hannover.fholz.studentenklatschen.R;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.Attributes.*;

//Klasse für Item-Eigenschaften mit voreingestellten Werten

public class Affix {

    public Map<String, Integer> factors;
    public String name;
    public static String[] affixName = {"der Mensafrau", "des Bücherwurms", "vong 1 Lauch", "des Kaffeetrinkers"};
    public static int[][] affixAttribute = {{2,0,0},{0,2,0},{-10,-10,-10},{0,0,2}};

    public Affix(String name, int str, int def, int spc){
        factors = new HashMap<>();
        factors.put(attributeName[STRENGTH], str);
        factors.put(attributeName[DEFENSE], def);
        factors.put(attributeName[SPECIAL], spc);
        this.name = name;
    }

    public static Affix genAffix(){
        Random random = new Random();
        int rnd = random.nextInt(4);
        Affix affix = new Affix(affixName[rnd], affixAttribute[rnd][STRENGTH], affixAttribute[rnd][DEFENSE], affixAttribute[rnd][SPECIAL]);
        return affix;
    }
}
