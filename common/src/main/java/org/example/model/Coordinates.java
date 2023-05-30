/*
Для работы с координатами
 */
package org.example.model;

import org.example.utily.Valadatable;

import java.io.Serializable;

public class Coordinates implements Serializable, Valadatable {
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

    @Override
    public boolean validate() {
        if (x == null) return false;
        return true;
    }
}
