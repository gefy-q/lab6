/*
Действие для добавления элемента в коллекцию
 */
package org.example.src.ui.requests;

import org.example.src.controllers.CollectionController;
import org.example.src.representations.console.ConsoleDragonRepr;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class AddAction extends Action {
    private final CollectionController controller;

    public AddAction(CollectionController controller, String args, String description) {
        super(args, description);
        this.controller = controller;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) throws IOException {
        writer.write("Enter dragon fields\n");
        writer.flush();
        controller.add(ConsoleDragonRepr.readAsDragon(scanner, writer, controller.generateId()));
        writer.write("Dragon has been added\n");
        writer.flush();
        return true;
    }
}
