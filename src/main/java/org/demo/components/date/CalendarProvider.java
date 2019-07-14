package org.demo.components.date;

import org.demo.entities.date.Day;
import org.demo.entities.date.Month;
import org.demo.exceptions.InvalidDayInYearException;
import org.demo.exceptions.InvalidFiscalDateFormatException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class CalendarProvider implements ICalendarProvider {
    @Override
    public List<Month> getMonthes(int fiscalDateFrom, int fiscalDateTo) throws InvalidFiscalDateFormatException {
        Calendar firstDate = Calendar.getInstance();
        Calendar latsDate = Calendar.getInstance();
        List<Month> months = new ArrayList<>();
        firstDate.set(fiscalDateFrom / 100, (fiscalDateFrom % 100) - 1, 1);
        latsDate.set(fiscalDateTo / 100, (fiscalDateTo % 100) - 1, 1);

        while (firstDate.get(Calendar.YEAR) < latsDate.get(Calendar.YEAR) ||
                firstDate.get(Calendar.MONTH) < latsDate.get(Calendar.MONTH)) {
            months.add(new Month(firstDate.get(Calendar.MONTH), firstDate.get(Calendar.YEAR)));
            firstDate.add(Calendar.MONTH, 1);
        }
        months.add(new Month(latsDate.get(Calendar.MONTH), latsDate.get(Calendar.YEAR)));

        return months;
    }

    @Override
    public Month getDayInfo(int year, int dayInYear) throws InvalidDayInYearException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 0);
        calendar.add(Calendar.DAY_OF_MONTH, dayInYear);
        Month month = new Month(calendar.getTime().getMonth(), year);
        month.addDay(new Day(calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.DAY_OF_MONTH)));
        return month;
    }

    @Override
    public List<String> getMonthesFormatted(int fiscalDateFrom, int fiscalDateTo) throws InvalidFiscalDateFormatException {
        List<Month> months = getMonthes(fiscalDateFrom, fiscalDateTo);

        return null;
    }
}
