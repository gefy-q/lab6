/*
Удаляет самый большой элемент
 */
package org.example.src.menu.actions;

import org.example.src.controllers.CollectionController;
import org.example.src.representations.console.ConsoleDragonRepr;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class RemoveGreaterAction extends Action {
    private final CollectionController controller;

    public RemoveGreaterAction(CollectionController controller, String args, String description) {
        super(args, description);
        this.controller = controller;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) throws IOException {
        controller.removeGreater(ConsoleDragonRepr.readAsDragon(scanner, writer, controller.generateId()));
        return true;
    }
}
