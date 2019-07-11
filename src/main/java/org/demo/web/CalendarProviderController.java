package org.demo.web;

import org.demo.components.CalendarProvider;
import org.demo.entities.Bird;
import org.demo.entities.Month;
import org.demo.exceptions.InvalidDayInYearException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main")
public class CalendarProviderController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarProviderController.class);
    private static final CalendarProvider CALENDAR_PROVIDER = new CalendarProvider();

    public CalendarProviderController() {
        logger.info("CalendarProviderController created");
    }

    /*
    * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/get-day-info?year=1983&dayInYear=26*/

    @RequestMapping(value = "get-day-info", method = RequestMethod.GET)
    @ResponseBody
    public Month getDayInfo(int year, int dayInYear) throws InvalidDayInYearException {
        return CALENDAR_PROVIDER.getDayInfo(year, dayInYear);
    }
}
