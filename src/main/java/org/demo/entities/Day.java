package org.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private final static String[] DAY_NAMES= new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private int numInWeek;
    private int numInmonth;
    private String name;

    public Day(int numInWeek, int numInmonth) {
        this.numInWeek = numInWeek;
        this.numInmonth = numInmonth;
        this.name = DAY_NAMES[numInWeek];
    }
}
