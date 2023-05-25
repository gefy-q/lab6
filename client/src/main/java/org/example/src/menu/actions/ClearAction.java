/*
Очищает коллекцию
 */

package org.example.src.menu.actions;

import org.example.src.controllers.CollectionController;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ClearAction extends Action {
    private final CollectionController controller;

    public ClearAction(CollectionController controller, String args, String description) {
        super(args, description);
        this.controller = controller;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) {
        controller.clear();
        return true;
    }
}
