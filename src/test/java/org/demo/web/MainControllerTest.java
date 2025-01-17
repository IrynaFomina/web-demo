package org.demo.web;

import org.demo.entities.Bird;
import org.junit.Assert;
import org.junit.Test;

public class MainControllerTest {
//    private static MainController mainController;

    @Test
    public void addNewBird() {
        MainController mainController = new MainController();
        Assert.assertFalse(mainController.addNewBird(null, null,null));
        Assert.assertTrue(mainController.addNewBird("bird1", "area1",2d ));
        Assert.assertFalse(mainController.addNewBird("bird1", "area1",2d ));
    }

    @Test
    public void searchBird(){
        MainController mainController = new MainController();
        mainController.addNewBird("bird1", "area1",2d );
        Assert.assertEquals("bird1", mainController.getBird("bird1").getName());
    }

    @Test
    public void deleteBird(){
        MainController mainController = new MainController();
        Assert.assertFalse(mainController.deleteBird("bird1"));
        Assert.assertTrue(mainController.addNewBird("bird1", "area1",2d ));
        Assert.assertTrue(mainController.deleteBird("bird1"));
        Assert.assertFalse(mainController.deleteBird("bird1"));
    }

    @Test
    public void updateBird(){
        MainController mainController = new MainController();
        Assert.assertFalse(mainController.updateBird("bird2", "area2", 1d));
        Assert.assertTrue(mainController.addNewBird("bird2", "area1", 2d));
        Assert.assertTrue(mainController.updateBird("bird2", "area2", 1d));
        Assert.assertEquals(new Bird("bird2", "area2", 1d), mainController.getBird("bird2") );
    }
}