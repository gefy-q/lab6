/*
Удаляет элемент с определенной позиции
 */
package org.example.requests;

import org.example.utily.Actions;

public class RemoveByIdAction extends Action {
    private final int id;

    public RemoveByIdAction(int id) {
        super(Actions.REMOVE_BY_ID);
        this.id = id;
    }

}
