package org.demo.components;

import org.demo.entities.Day;
import org.demo.entities.Month;
import org.demo.exceptions.InvalidDayInYearException;
import org.demo.exceptions.InvalidFiscalDateFormatException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class CalendarProvider implements ICalendarProvider {
    @Override
    public List<Month> getMonthes(int fiscalDateFrom, int fiscalDateTo) throws InvalidFiscalDateFormatException {
        return null;
    }

    @Override
    public Month getDayInfo(int year, int dayInYear) throws InvalidDayInYearException {
//        Fail in logic
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setDate(dayInYear);
        date.setYear(year);
        Month month = new Month(date.getMonth(),year);
        System.out.println("########### Month :" + month);
        month.addDay(new Day(calendar.get(Calendar.DAY_OF_WEEK),calendar.get(Calendar.DAY_OF_MONTH)));
        return month;
    }

    @Override
    public List<String> getMonthesFormatted(int fiscalDateFrom, int fiscalDateTo) throws InvalidFiscalDateFormatException {
        return null;
    }
}
