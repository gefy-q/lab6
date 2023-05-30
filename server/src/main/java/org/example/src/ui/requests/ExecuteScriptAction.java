/*
Для работы со скриптом и выполнения команд оттуда. Перенаправляет действия в menu и работает, как с вводом данных
 */

package org.example.src.ui.requests;

import org.example.src.menu.Menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Scanner;

public class ExecuteScriptAction extends Action {
    Menu menu;

    public ExecuteScriptAction(Menu menu, String args, String description) {
        super(args, description);
        this.menu = menu;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) throws IOException {
        menu.run(new InputStreamReader(new FileInputStream(scanner.next())), writer);
        return true;
    }
}
