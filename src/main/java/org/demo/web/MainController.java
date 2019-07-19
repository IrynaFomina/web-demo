package org.demo.web;

import org.demo.entities.Bird;
import org.demo.components.BirdStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    /*TODO: remove singleton*/
    private BirdStore BIRD_STORE=new BirdStore();


    public MainController() {
        logger.info("Controller created");
//        BirdStore BIRD_STORE = new BirdStore();
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

    /**
     * Add new birds
     *
     * @param1 - bird name
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/add-new-bird?name=lola&livingArea=aaa&size=2
     */
    @RequestMapping(value = "add-new-bird", method = RequestMethod.PUT)
    @ResponseBody
    public boolean addNewBird(String name, String livingArea, Double size) {
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


    /**
     * Delete exist Bird
     *
     * @param1 - bird name
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/delete-bird?name=lola
     */
    @RequestMapping(value = "delete-bird", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteBird(String name) {
        if (null != name) {
            logger.error("Bird name isn't defined");
            return BIRD_STORE.deleteBird(BIRD_STORE.searchByName(name));
        }
        return false;
    }

    /**
     * Search a bird by name
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/find-bird?name=lola
     */
    @RequestMapping(value = "find-bird", method = RequestMethod.GET)
    @ResponseBody
    public Bird getBird(String name) {
        if (null == name) {
            logger.error("Bird name isn't defined");
            return null;
        }
        return BIRD_STORE.searchByName(name);
    }

    /**
     * Update a bird
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/add-new-bird?name=lola&livingArea=bb&size=1
     */
    @RequestMapping(value = "update-bird", method = RequestMethod.GET)
    @ResponseBody
    public boolean updateBird(String name, String livingArea, Double size) {
        return BIRD_STORE.updateBird(name, livingArea, size);
    }
}
