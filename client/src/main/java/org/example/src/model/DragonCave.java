/*
Для заполнения сокровищ
 */
package org.example.src.model;

public class DragonCave {
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
