/*
Выводит все команды, которые получает из менюшки
 */

package org.example.responses;

import org.example.utily.Actions;

public class HelpAction extends Action {
    private final String message;

    public HelpAction(String message, String error) {
        super(Actions.HELP, error);
        this.message = message;
    }

}
