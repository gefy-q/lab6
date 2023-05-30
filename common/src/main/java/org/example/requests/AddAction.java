/*
Действие для добавления элемента в коллекцию
 */
package org.example.requests;

import org.example.utily.Actions;
import org.example.model.Dragon;

public class AddAction extends Action {
    private final Dragon dragon;

    public AddAction(Dragon dragon) {
        super(Actions.ADD);
        this.dragon = dragon;
    }

}
