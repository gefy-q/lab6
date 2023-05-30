/*
Удаляет самый большой элемент
 */
package org.example.responses;

import org.example.utily.Actions;

public class RemoveGreaterAction extends Action {
    private final int numberOfTreasure;

    public RemoveGreaterAction(int numberOfTreasure, String error) {
        super(Actions.REMOVE_GREATER, error);
        this.numberOfTreasure = numberOfTreasure;
    }

}
