/*
Для заполнения сокровищ
 */
package org.example.model;

import java.io.Serializable;

public class DragonCave implements Serializable {
    private final long numberOfTreasures;

    public DragonCave(long numberOfTreasures) {
        if (numberOfTreasures <= 0) {
            throw new IllegalArgumentException("Number of treasures must be greater than 0");
        }

        this.numberOfTreasures = numberOfTreasures;
    }

    public long getNumberOfTreasures() {
        return numberOfTreasures;
    }
}
