/*
Виды дракончиков
 */
package org.example.model;

import java.io.Serializable;

public enum DragonCharacter implements Serializable {
    CUNNING,
    WISE,
    GOOD,
    FICKLE;

    public static String adjectives() {
        StringBuilder adjList = new StringBuilder();
        for (var type : values()){
            adjList.append(type.name()).append(", ");
        }
        return adjList.substring(0, adjList.length()-2);
    }
}
