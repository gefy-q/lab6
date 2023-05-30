/*
Удаляет элемент с определенной позиции
 */
package org.example.responses;

import org.example.utily.Actions;

public class RemoveByIdAction extends Action {

    public RemoveByIdAction(String error) {
        super(Actions.REMOVE_BY_ID, error);
    }

}
