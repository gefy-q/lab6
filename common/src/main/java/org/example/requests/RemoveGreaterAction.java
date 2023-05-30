/*
Удаляет самый большой элемент
 */
package org.example.requests;

import org.example.utily.Actions;

public class RemoveGreaterAction extends Action {
    private final int numberOfTreasure;

    public RemoveGreaterAction(int numberOfTreasure) {
        super(Actions.REMOVE_GREATER);
        this.numberOfTreasure = numberOfTreasure;
    }
}
