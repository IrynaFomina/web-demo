package org.demo.components.date;

import org.demo.entities.date.Day;
import org.demo.entities.date.Month;
import org.demo.exceptions.InvalidDayInYearException;
import org.demo.exceptions.InvalidFiscalDateFormatException;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CalendarProviderTest {

    @Test
    public void getMonthes() throws InvalidFiscalDateFormatException {
        CalendarProvider calendarProvider = new CalendarProvider();
        List<Month> months = new ArrayList<>();
        months.add(new Month(0,2019));
        months.add(new Month(1,2019));
        months.add(new Month(2,2019));
        List<Month> monthsResult = calendarProvider.getMonthes(201901,201903);
        Assert.assertEquals(3,monthsResult.size() );
        Assert.assertEquals(months.get(0),monthsResult.get(0));
        Assert.assertEquals(months.get(1),monthsResult.get(1));
        Assert.assertEquals(months.get(2),monthsResult.get(2));
    }

    @Test
    public void getDayInfo() throws InvalidDayInYearException {
        CalendarProvider calendarProvider = new CalendarProvider();
        Month month = calendarProvider.getDayInfo(2019, 33);
        Assert.assertEquals(1, month.getNum());
        Assert.assertEquals("February", month.getName());
        Assert.assertEquals(2019, month.getYear());
        Assert.assertEquals(1, month.getDays().size());
        Assert.assertEquals(new Day(7, 2), month.getDays().get(0));
    }

    @Test
    public void getMonthesFormatted() throws InvalidFiscalDateFormatException {
        CalendarProvider calendarProvider = new CalendarProvider();
        List<String> resultList = calendarProvider.getMonthesFormatted(201801, 201803);
        Assert.assertEquals("201801",resultList.get(0));
        Assert.assertEquals("201802",resultList.get(1));
        Assert.assertEquals("201803",resultList.get(2));
    }
}