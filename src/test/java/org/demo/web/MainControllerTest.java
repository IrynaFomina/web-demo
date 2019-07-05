package org.demo.web;

import org.demo.components.BirdStore;
import org.demo.entities.Bird;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class MainControllerTest {
//    private static MainController mainController;

    @Test
    public void addNewBird() {
        MainController mainController = new MainController();
        BirdStore birdStore = BirdStore.of();
        Assert.assertFalse(mainController.addNewBird(null, null,null));
        Assert.assertTrue(mainController.addNewBird("bird1", "area1",2d ));
        Assert.assertFalse(mainController.addNewBird("bird1", "area1",2d ));
        birdStore = null;
    }

    @Test
    public void searchBird(){
        MainController mainController = new MainController();
        BirdStore birdStore = BirdStore.of();
        mainController.addNewBird("bird1", "area1",2d );
        Assert.assertEquals("bird1", mainController.getBird("bird1").getName());
        birdStore = null;
    }
}