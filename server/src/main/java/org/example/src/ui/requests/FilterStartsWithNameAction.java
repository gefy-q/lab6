/*
Выводит дракончиков по заданному имени
 */
package org.example.src.ui.requests;

import org.example.src.controllers.CollectionController;
import org.example.src.model.Dragon;
import org.example.src.representations.console.ConsoleDragonRepr;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FilterStartsWithNameAction extends Action {
    CollectionController controller;

    public FilterStartsWithNameAction(CollectionController controller, String args, String description) {
        super(args, description);
        this.controller = controller;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) throws IOException {
        String name = scanner.next().trim();
        ArrayList<Dragon> dragons = new ArrayList<>();
        for (Dragon dragon : controller) {
            if (dragon.getName().startsWith(name)) {
                dragons.add(dragon);
            }
        }
        if (dragons.isEmpty()) {
            writer.write("No dragons found\n");
            writer.flush();
            return true;
        }
        ConsoleDragonRepr.show(scanner, writer, dragons);
        return true;
    }
}

