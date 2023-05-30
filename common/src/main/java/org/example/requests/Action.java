/*
Класс - основа для действий.
*/
package org.example.requests;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Scanner;

public abstract class Action implements Serializable {
    protected final String name;

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
