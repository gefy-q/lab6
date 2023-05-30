/*
Для работы со скриптом и выполнения команд оттуда. Перенаправляет действия в menu и работает, как с вводом данных
 */

package org.example.requests;

import org.example.utily.Actions;

public class ExecuteScriptAction extends Action {

    public ExecuteScriptAction() {
        super(Actions.EXECUTE_SCRIPT);
    }
}
