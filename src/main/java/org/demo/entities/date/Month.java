package org.demo.entities.date;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
