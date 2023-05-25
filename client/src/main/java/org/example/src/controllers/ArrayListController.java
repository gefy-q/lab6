/*
Действия, связанные с коллекцией
 */

package org.example.src.controllers;



import org.example.src.model.Dragon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayListController implements CollectionController {
    private final ArrayList<Dragon> dragons = new ArrayList<>();
    private final LocalDateTime initTime = LocalDateTime.now();

    private int findIndexById(Integer id) {
        for (int i = 0; i < dragons.size(); ++i) {
            if (dragons.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexById(Integer id) {
        int index = findIndexById(id);
        if (id == -1) {
            throw new NoSuchElementException();
        }
        return index;
    }

    @Override
    public LocalDateTime getInitTime() {
        return this.initTime;
    }

    @Override
    public int size() {
        return dragons.size();
    }

    @Override
    public void add(Dragon dragon) {
        dragons.add(dragon);
    }

    @Override
    public boolean containsId(Integer id) {
        return findIndexById(id) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void updateById(Integer id, Dragon dragon) {
        dragons.set(getIndexById(id), dragon);
    }

    @Override
    public void removeById(Integer id) {
        dragons.remove(getIndexById(id));
    }

    @Override
    public void clear() {
        dragons.clear();
    }

    @Override
    public void insertAt(int index, Dragon dragon) {
        dragons.add(index, dragon);
    }

    @Override
    public void addIfMax(Dragon dragon) {
        for (Dragon value : dragons) {
            if (value.getCave().getNumberOfTreasures() >= dragon.getCave().getNumberOfTreasures()) {
                return;
            }
        }

        add(dragon);
    }

    @Override
    public void removeGreater(Dragon dragon) {
        dragons.removeIf(x -> x.getCave().getNumberOfTreasures() > dragon.getCave().getNumberOfTreasures());
    }

    @Override
    public int countByAge(Integer age) {
        int count = 0;
        for (Dragon dragon : dragons) {
            if (dragon.getAge().equals(age)) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public int countLessThanWingspan(Double wingspan) {
        int count = 0;
        for (Dragon dragon : dragons) {
            if (dragon.getWingspan() < wingspan) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public int generateId() {
        while (true) {
            int id = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
            if (!containsId(id)) {
                return id;
            }
        }
    }

    @Override
    public Iterator<Dragon> iterator() {
        return dragons.iterator();
    }
}
