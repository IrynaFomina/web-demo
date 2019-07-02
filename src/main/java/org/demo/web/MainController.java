package org.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("src/main")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController() {
        logger.info("Controller created");
    }

    @RequestMapping("get-some-data")
    @ResponseBody
    public String[] getSomeData() {
        logger.info("Logger: Controller method invoked");
        return new String[]{"1111", "2222", "333"};
    }
}
