/*
Выводит информацию о коллекции
 */
package org.example.responses;

import org.example.utily.Actions;

public class InfoAction extends Action {
    private final String message;

    public InfoAction(String message, String error) {
        super(Actions.INFO, error);
        this.message = message;
    }

}
