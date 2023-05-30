/*
Добавить элемент, если введенный, больше наибольшего в коллекции. Часть исполнения в ArrayListController.
 */

package org.example.requests;

import org.example.udpclient.utily.Actions;
import org.example.udpclient.model.Dragon;

public class AddIfMaxAction extends Action {
    private final Dragon dragon;

    public AddIfMaxAction(Dragon dragon) {
        super(Actions.ADD_IF_MAX);
        this.dragon = dragon;
    }
}
