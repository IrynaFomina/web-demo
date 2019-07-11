package org.demo.entities;

import java.util.LinkedList;
import java.util.List;

public class Month {
//    TODO: add all names
    private final static String[] MONTH_NAMES= new String[]{"Jan","Feb","March"};
    private String name;
    private int num;
    private int year;
    private List<Day> days;

    public Month(int num, int year) {
        this.num = num;
        this.year = year;
        this.name = MONTH_NAMES[num];
        days = new LinkedList<>();
    }

    public void addDay (Day day){
        days.add(day);
    }


}
