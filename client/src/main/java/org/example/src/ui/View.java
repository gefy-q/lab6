/*
Информация по командам
 */
package org.example.src.ui;

import org.example.src.controllers.CollectionController;
import org.example.src.menu.Menu;
import org.example.src.menu.actions.*;

import java.io.*;
import java.nio.file.Path;

public class View {
    private final Menu menu;

    public View(Path dataFile, CollectionController controller) {
        menu = new Menu();
        menu.addAction("help", new HelpAction(menu, "", "show help information for available commands"));
        menu.addAction("info", new InfoAction(controller, "", "show collection info"));
        menu.addAction("show", new ShowAction(controller, "", "show dragons"));
        menu.addAction("add", new AddAction(controller, "{element}", "add new element"));
        menu.addAction("update", new UpdateByIdAction(controller, "id {element}", "update element by id"));
        menu.addAction("remove_by_id", new RemoveByIdAction(controller, "id", "remove element by id"));
        menu.addAction("clear", new ClearAction(controller, "", "clear collection"));
        menu.addAction("save", new SaveAction(dataFile, controller, "", "save data to file"));
        menu.addAction("execute_script", new ExecuteScriptAction(menu, "file_name", "execute script from file"));
        menu.addAction("exit", new ExitAction("", "exit from program"));
        menu.addAction("insert_at", new InsertAtAction(controller, "index {element}", "insert dragon at index"));
        menu.addAction("add_if_max", new AddIfMaxAction(controller, "{element}", "insert element if is greater"));
        menu.addAction("remove_greater", new RemoveGreaterAction(controller, "{element}", "remove greater than this"));
        menu.addAction("count_by_age", new CountByAgeAction(controller, "age", "count dragons with this age"));
        menu.addAction("count_less_than_wingspan", new CountLessThanWingspanAction(controller, "wingspan", "count dragons with less wingspan"));
        menu.addAction("filter_starts_with_name", new FilterStartsWithNameAction(controller, "name", "filter dragons by name"));
    }

    public void run(Reader reader, Writer writer, String command) {
        try {
            writer.write("Welcome! Enter the command. To show instructions type \"help\"\n");
            writer.flush();
            menu.run(reader, writer, command);
        } catch (IOException e) {
            System.err.println("Error. " + e.getMessage());
        }
    }
}
