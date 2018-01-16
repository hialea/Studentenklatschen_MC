package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Franzi on 10.01.2018.
 */

public class History {

    public static String fight = "fight";
    public static String travel = "travel";

    public static class HistoryType {


        public Profile opponent;
        public boolean won;
        public int exp;
        public int klatschis;
        public Date date;

        public HistoryType(Profile opponent, boolean won, int exp, int klatschis, Date date) {
            this.opponent = opponent;
            this.won = won;
            this.exp = exp;
            this.klatschis = klatschis;
            this.date = date;
        }

        public int duration;

        public HistoryType(int duration, int klatschies, Date date) {
            this.duration = duration;
            this.klatschis = klatschies;
            this.date = date;
        }
    }

    public Map<String, ArrayList<HistoryType>> history;
    public ArrayList<HistoryType> fightSessions;
    public ArrayList<HistoryType> travelSessions;

    public History(){

        this.fightSessions = new ArrayList<>();
        this.travelSessions = new ArrayList<>();
        this.history = new HashMap<>();
        history.put(fight, fightSessions);
        history.put(travel, fightSessions);

    }
}
