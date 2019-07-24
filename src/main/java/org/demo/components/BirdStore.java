package org.demo.components;

import org.demo.entities.Bird;
import org.demo.exceptions.BirdStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Отнаследоваться от AbstractBirdStore.
 * <p>
 * Реализовать паттерн Singleton.
 */
@Component
public class BirdStore extends AbstractBirdStore {
    //    private static final BirdStore birdStore = new BirdStore();
    private Map<String, Bird> listNames;
    private Map<String, List<Bird>> listLivingAreas;
    private static final Logger logger = LoggerFactory.getLogger(BirdStore.class);
    public BirdStore() {
        listNames = new HashMap<>();
        listLivingAreas = new HashMap<>();
    }

//    public static BirdStore of() {
//        return birdStore;
//    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BirdStore{");
        sb.append("listNames=").append(listNames);
        sb.append(", listLivingAreas=").append(listLivingAreas);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public synchronized boolean addBird(Bird b) throws BirdStoreException {
        if (!listNames.containsKey(b.getName())) {
            listNames.put(b.getName(), b);
        } else {
            throw new BirdStoreException("Bird with name " + b.getName() + "is already exist");
        }
        List<Bird> list;
        if (listLivingAreas.containsKey(b.getLivingArea())) {
            list = listLivingAreas.get(b.getLivingArea());
            list.add(b);
        } else {
            list = new ArrayList<>();
            list.add(b);
            listLivingAreas.put(b.getLivingArea(), list);
        }
        return true;
    }


    @Override
    public Bird searchByName(String nameToSearch) {
        if (listNames.containsKey(nameToSearch)) {
            return listNames.get(nameToSearch);
        }
        return null;
    }

    @Override
    public List searchByLivingArea(String livingAreaToFind) {
        if (listLivingAreas.containsKey(livingAreaToFind)) {
            return listLivingAreas.get(livingAreaToFind);
        }
        return null;
    }

//    @Override
//    public synchronized boolean deleteBird(Bird b) throws BirdStoreException {
//        logger.error("!!!!!INFO !!!!!! :" + b.toString());
//        if (listNames.size() > 0) {
//            if (!listNames.remove(b.getName(), b)) {
//                throw new BirdStoreException("Bird with name " + b.getName() + "do not exist");
//            }
//            listLivingAreas.get(b.getLivingArea()).remove(b);
//            return true;
//        }
//        return false;
//    }

    @Override
    public synchronized boolean deleteBird(String b) throws BirdStoreException {
        logger.error("!!!!!INFO !!!!!! :" + b.toString());
        if (listNames.size() > 0) {
            Bird birdToDelete = searchByName(b);
            if (birdToDelete != null) {
                listNames.remove(b, birdToDelete);
                listLivingAreas.get(birdToDelete.getLivingArea()).remove(birdToDelete);
                return true;
            }
        }
        throw new BirdStoreException("Bird with name " + b + "do not exist");
    }

    @Override
    public boolean updateBird(String name, String livingArea, double size) {
        try {
            if (deleteBird(name)) {
                return addBird(new Bird(name, livingArea, size));
            }
        } catch (BirdStoreException e) {
            e.printStackTrace();
        }
        return false;
    }
}
