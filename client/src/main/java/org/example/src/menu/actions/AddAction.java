/*
Действие для добавления элемента в коллекцию
 */
package org.example.src.menu.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.src.controllers.CollectionController;
import org.example.src.representations.console.*;
import org.example.src.model.*;


import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
