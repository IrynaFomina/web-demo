package org.demo.components;

import org.demo.entities.Bird;
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
    public synchronized boolean addBird(Bird b) {
        if (!listNames.containsKey(b.getName())) {
            listNames.put(b.getName(), b);
        } else {
            System.out.println("Bird with name " + b.getName() + "is already exist");
            return false;
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

    @Override
    public synchronized boolean deleteBird(Bird b) {
        if (listNames.size()>0) {
            if (!listNames.remove(b.getName(), b)) {
                System.out.println("Bird with name " + b.getLivingArea() + "do not exist");
                return false;
            }
            listLivingAreas.get(b.getLivingArea()).remove(b);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBird(String name,String livingArea, double size) {
        if (deleteBird(searchByName(name))) {
            return addBird(new Bird(name, livingArea, size));
        }
        return false;
    }
}
