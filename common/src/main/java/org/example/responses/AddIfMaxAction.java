/*
Добавить элемент, если введенный, больше наибольшего в коллекции. Часть исполнения в ArrayListController.
 */

package org.example.responses;

import org.example.utily.Actions;

public class AddIfMaxAction extends Action {
    private final boolean isAdded;
    private final int id;

    public AddIfMaxAction(boolean isAdded, int id, String error) {
        super(Actions.ADD_IF_MAX, error);
        this.id = id;
        this.isAdded = isAdded;
    }
}
