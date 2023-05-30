/*
Класс - основа для действий.
*/
package org.example.responses;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Scanner;

public abstract class Action implements Serializable {
    protected final String name;
    protected final String error;

    public Action(String name, String error) {
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public String getError() {
        return error;
    }

}
