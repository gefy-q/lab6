/*
Очищает коллекцию
 */

package org.example.responses;

import org.example.utily.Actions;

public class ClearAction extends Action {

    public ClearAction(String error) {
        super(Actions.CLEAR, error);
    }

}
