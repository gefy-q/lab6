/*
Добавляет новый элемент, но на определенную позицию
 */
package org.example.responses;

import org.example.utily.Actions;

public class InsertAtAction extends Action {
    private final int index;

    public InsertAtAction(int index, String error) {
        super(Actions.INSERT_AT, error);
        this.index = index;
    }

}
