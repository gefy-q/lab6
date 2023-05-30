/*
Добавляет новый элемент, но на определенную позицию
 */
package org.example.requests;

import org.example.utily.Actions;

public class InsertAtAction extends Action {
    private final int index;

    public InsertAtAction(int index) {
        super(Actions.INSERT_AT);
        this.index = index;
    }

}
