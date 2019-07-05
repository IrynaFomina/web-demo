package main.java.org.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("main")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    public MainController() {
        LOGGER.info("Controller created");
    }

    @RequestMapping("get-some-data")
    @ResponseBody
    public String[] getSomeData() {
        LOGGER.info("Controller method invoked");
        return new String[]{"1111", "2222", "333"};
    }
}
