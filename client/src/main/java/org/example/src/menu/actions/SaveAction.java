/*
Сохраняет коллекцию в переданный файл
 */
package org.example.src.menu.actions;

import org.example.src.controllers.CollectionController;
import org.example.src.representations.json.JsonCollectionControllerRepr;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class SaveAction extends Action {
    private final Path outputFile;
    private final CollectionController controller;

    public SaveAction(Path outputFile, CollectionController controller, String args, String description) {
        super(args, description);
        this.outputFile = outputFile;
        this.controller = controller;
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) throws IOException {
        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile.toString())))) {
            JsonCollectionControllerRepr.write(fileWriter, controller);
            fileWriter.flush();
            writer.write("Successfully saved\n");
            writer.flush();
        } catch (IOException e) {
            throw new IOException(String.format("Cannot save data to file \"%s\"", outputFile));
        }
        return true;
    }
}
