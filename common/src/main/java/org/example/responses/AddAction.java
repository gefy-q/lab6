/*
Действие для добавления элемента в коллекцию
 */
package org.example.responses;

import org.example.utily.Actions;

public class AddAction extends Action {
    private final int id;

    public AddAction(int id, String error) {
        super(Actions.ADD, error);
        this.id = id;
    }

}
