package org.demo.entities.date;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class Day {
    private final static String[] DAY_NAMES= new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    @NonNull
    private int numInWeek;
    @NonNull @Min(value = 1) @Max(value = 31)
    private int numInmonth;
    private String name;

    public Day(int numInWeek, int numInmonth) {
        this.numInWeek = numInWeek;
        this.numInmonth = numInmonth;
        this.name = DAY_NAMES[numInWeek-1];
    }

    public int getNumInWeek() {
        return numInWeek;
    }

    public void setNumInWeek(int numInWeek) {
        this.numInWeek = numInWeek;
    }

    public int getNumInmonth() {
        return numInmonth;
    }

    public void setNumInmonth(int numInmonth) {
        this.numInmonth = numInmonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return numInWeek == day.numInWeek &&
                numInmonth == day.numInmonth &&
                Objects.equals(name, day.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numInWeek, numInmonth, name);
    }
}
