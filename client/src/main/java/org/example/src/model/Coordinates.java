/*
Для работы с координатами
 */
package org.example.src.model;

public class Coordinates {
    private final Long x;
    private final double y;

    public Coordinates(Long x, double y) {
        if (x == null) {
            throw new NullPointerException("X coordinate cannot be null");
        }

        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
