/*
Изменение id дракончика
 */

package org.example.requests;

import org.example.udpclient.utily.Actions;
import org.example.udpclient.model.Dragon;

public class UpdateByIdAction extends Action {
    private final int id;
    public final Dragon updatedDragon;

    public UpdateByIdAction(int id, Dragon updatedDragon) {
        super(Actions.UPDATE_BY_ID);
        this.id = id;
        this.updatedDragon = updatedDragon;
    }

}
