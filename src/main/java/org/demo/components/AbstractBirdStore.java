package org.demo.components;

import org.demo.entities.Bird;

import java.util.List;

public abstract class AbstractBirdStore {
    /**
     * Добавить новый объект
     * @param b объект который должен быть добьавлен.
     *
     * Если объект с тактм именем уже существует - вывести Bird With name .... already exists.
     */
    public abstract boolean addBird(Bird b);

    /**
     * Вернуть объект у которого name = nameToSearch
     * @param nameToSearch имя по которому нужно найти объект.
     * @return обьект Bird или null если не существует объекта с таким nameToSearch
     */
    public abstract Bird searchByName(String nameToSearch);

    /**
     * Вернуть все объекты у которых livingArea = livingAreaToFind
     * @param livingAreaToFind areaLiving по которому нужно найти все объекты.
     * @return все объекты у которых livingArea = livingAreaToFind
     */
    public abstract List searchByLivingArea(String livingAreaToFind);

    public abstract boolean deleteBird(Bird b);

    public abstract boolean updateBird(String name,String livingArea, double size);
}
