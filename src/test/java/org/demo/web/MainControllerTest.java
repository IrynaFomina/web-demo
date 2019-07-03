package test.java.org.demo.web;

import main.java.org.demo.web.MainController;
import org.junit.Assert;
import org.junit.Test;


public class MainControllerTest {

    @Test
    public void addNewBird() {
        MainController mainController = new MainController();
        Assert.assertFalse(mainController.addNewBird(null, null,null ));
        Assert.assertTrue(mainController.addNewBird("bird1", "area1",2d ));
        Assert.assertFalse(mainController.addNewBird("bird1", "area1",2d ));
    }
}