package org.demo.web;

import org.demo.components.date.CalendarProvider;
import org.demo.entities.date.Month;
import org.demo.exceptions.InvalidDayInYearException;
import org.demo.exceptions.InvalidFiscalDateFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("date")
public class CalendarProviderController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarProviderController.class);
    private static final CalendarProvider CALENDAR_PROVIDER = new CalendarProvider();

    public CalendarProviderController() {
        logger.info("CalendarProviderController created");
    }


//    http://localhost:8080/web-demo-1.0-SNAPSHOT/date/get-months?fiscalDateFrom=201801&fiscalDateTo=201803
    @RequestMapping(value = "get-months", method = RequestMethod.GET)
    @ResponseBody
    public List<Month> getMonthes(int fiscalDateFrom, int fiscalDateTo) throws InvalidFiscalDateFormatException {
        fiscalDateChecker(fiscalDateFrom);
        fiscalDateChecker(fiscalDateTo);
        return CALENDAR_PROVIDER.getMonthes(fiscalDateFrom,fiscalDateTo);
    }

    /*
    * http://localhost:8080/web-demo-1.0-SNAPSHOT/date/get-day-info?year=1983&dayInYear=26*/

    @RequestMapping(value = "get-day-info", method = RequestMethod.GET)
    @ResponseBody
    @Validated
//    @Valid @NotNull
    public Month getDayInfo(@Validated @NonNull Integer year, int dayInYear) throws InvalidDayInYearException {
//        getDayInfoChecker(year, dayInYear);
        return CALENDAR_PROVIDER.getDayInfo(year, dayInYear);
    }

    private void getDayInfoChecker(Integer year, Integer day) throws InvalidDayInYearException {
        if (!yearChecker(year)){
            throw new InvalidDayInYearException("Year should be positive");
        }
        else if (!(day > 0) && !(day<=365)){
            throw new InvalidDayInYearException("Day should have value from 1 to 365");
        }
    }

    private boolean yearChecker (int year){
        return (year > 0);
    }
    private boolean monthChecker (int month){
        return (month > 0 && month <13);
    }

    private void fiscalDateChecker (int fiscalDate) throws InvalidFiscalDateFormatException {
        if (!yearChecker(fiscalDate/100) || !monthChecker((fiscalDate % 100))){
            throw new InvalidFiscalDateFormatException("Incorrect fiscal date format");
        }
    }
}
