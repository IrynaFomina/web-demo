package main.java.org.demo.web;

import main.java.org.demo.entities.Bird;
import main.java.org.demo.store.BirdStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    /*TODO: remove singleton*/
    private static final BirdStore BIRD_STORE = BirdStore.of();


    public MainController() {
        logger.info("Controller created");
    }

//    @RequestMapping("get-some-data")
//    @ResponseBody
//    public String[] getSomeData() {
//        logger.info("Logger: Controller method invoked");
//        return new String[]{"1111", "2222", "333"};
//    }

//    @RequestMapping("get-some-data2")
//    @ResponseBody
//    public String[] getSomeData2() {
//        logger.info("Logger: Controller method invoked");
//        return new String[]{"1111", "2222", "333"};
//    }

    /*Add new birds
    *
    * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/add-new-bird?name=lola&livingArea=aaa&size=2
    * */
    @RequestMapping(value ="add-new-bird", method= RequestMethod.GET)
    @ResponseBody
    public boolean addNewBird(String name, String livingArea, Double size) {
        System.out.println("!!!!!!!!!!!!!!!!!!!Logger: Add New Bird action invoked");
        logger.info("Logger: Add New Bird action has started");
        if (null != name && null != livingArea && null != size) {
            if (BIRD_STORE.addBird(new Bird(name, livingArea, size))) {
                logger.info("New Bird with name" + name + " has been added");
                return true;
            }
            logger.warn("Bird with name " + name + " already exist");
            return false;
        }
        logger.error("Some params aren't defined");
        return false;
    }

}
