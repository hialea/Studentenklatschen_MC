package de.hs_hannover.fholz.studentenklatschen.Datamodel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static de.hs_hannover.fholz.studentenklatschen.Datamodel.History.HistoryType.fight;
import static de.hs_hannover.fholz.studentenklatschen.Datamodel.History.HistoryType.travel;

/**
 * Created by Franzi on 10.01.2018.
 */

public class History {

    public static class HistoryType {

        public static String fight = "Fight";
        public static String travel = "Travel2";
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

        public HistoryType(int duration, int exp, int klatschies, Date date) {
            this.duration = duration;
            this.exp = exp;
            this.klatschis = klatschies;
            this.date = date;
        }
    }

    public Map<String, ArrayList<HistoryType>> history;
    public ArrayList<HistoryType> fightSessions;
    public ArrayList<HistoryType> travelSessions;

    public History(){

        fightSessions = new ArrayList<>();
        travelSessions = new ArrayList<>();
        history = new HashMap<>();
        history.put(fight, fightSessions);
        history.put(travel, fightSessions);

    }
}
