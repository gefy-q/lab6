/*
Изменение id дракончика
 */

package org.example.responses;

import org.example.utily.Actions;

public class UpdateByIdAction extends Action {
    private final int id;

    public UpdateByIdAction(int id, String error) {
        super(Actions.UPDATE_BY_ID, error);
        this.id = id;
    }
}
