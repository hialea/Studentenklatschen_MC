package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import android.util.Log;

import java.util.Random;

public class Affix {
    public int[] factors = new int[3];
    public String name;
    public static String[] names = {"des Kaffeetrinkers", "des Bücherwurms", "vong 1 Lauch", "der Mensafrau"};
    public static int[][] attr = {{2,0,0},{0,2,0},{-10,-10,-10},{0,0,2}};

    public Affix(String name, int str, int def, int spc){
        factors[Attributes.Attr.STRENGTH] = str;
        factors[Attributes.Attr.DEFENSE] = def;
        factors[Attributes.Attr.SPECIAL] = spc;
        this.name = name;
    }

    public static Affix genAffix(){
        Log.d("myTag", "hallo");
        Random rsnd = new Random();
        int rnd = rsnd.nextInt(4);
        Log.d("myTag", String.valueOf(rnd));
        Affix affix = new Affix(names[rnd], attr[rnd][Attributes.Attr.STRENGTH], attr[rnd][Attributes.Attr.DEFENSE], attr[rnd][Attributes.Attr.SPECIAL]);
        return affix;
    }
}
