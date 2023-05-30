package org.example.utily;

import java.io.Serializable;

public abstract class Element implements Serializable, Valadatable, Comparable<Element> {
        abstract public int getId();
}