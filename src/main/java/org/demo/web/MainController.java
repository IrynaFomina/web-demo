package org.demo.web;

import org.demo.entities.Bird;
import org.demo.components.BirdStore;
import org.demo.exceptions.BirdStoreException;
import org.demo.web.responces.ErrorResponse;
import org.demo.web.responces.Response;
import org.demo.web.responces.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Controller
@RequestMapping("main")
@Validated
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private BirdStore BIRD_STORE; //=new BirdStore();
    private static final String NAME_PATTERN = "^[a-zA-Z\\s]{0,100}$";
    private static final String AREA_PATTERN = "^[a-zA-Z\\s]{0,50}$";

    @Autowired
    public void setBirdStore(BirdStore birdStore) {
        this.BIRD_STORE = birdStore;
    }

    public MainController() {
        logger.info("Controller created");
    }

    /**
     * Add new birds
     *
     * @param1 - bird name
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/add-new-bird?name=lola&livingArea=aaa&size=2
     */
    @RequestMapping(value = "add-new-bird", produces = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public Response<Bird> addNewBird(@Valid @Pattern(regexp = NAME_PATTERN,
            message = "Name can contain character and space symbols only") String name,
                               @Pattern(regexp = AREA_PATTERN) String livingArea,
                               @Positive Double size) throws BirdStoreException {
        logger.info("Logger: Add New Bird action has started");
        Bird newBird = new Bird(name, livingArea, size);
        if (BIRD_STORE.addBird(newBird)) {
            logger.info("New Bird with name" + name + " has been added");
            return new SuccessResponse<Bird>(newBird);
        }

        return new ErrorResponse(HttpStatus.SEE_OTHER,
                "Unexpected error");
    }

    /**
     * Delete exist Bird
     *
     * @param1 - bird name
     * http://localhost:8080/web-demo-1.0-SNAPSHOT/main/delete-bird?name=lola
     */
    @RequestMapping(value = "delete-bird", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteBird(String name) throws BirdStoreException {
        if (null != name) {
                return BIRD_STORE.deleteBird(name);
        }
        logger.error("Bird name isn't defined");
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
