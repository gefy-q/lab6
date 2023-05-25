package org.example.src;/*
Считывает файл по полученному пути и заполняет коллекцию, если файла нет - возвращает ошибку
После вызывает view-menu где происходят основные действия
 */

import org.example.src.controllers.CollectionController;
import org.example.src.controllers.ArrayListController;
import org.example.src.representations.json.JsonCollectionControllerRepr;
import org.example.src.ui.View;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App {

    public static void run(String file, String command) {


        final Path dataFile = Paths.get(file); // "C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\учеба\\прога\\352.json"
        CollectionController controller = new ArrayListController();

        try {
            JsonCollectionControllerRepr.read(new InputStreamReader(new FileInputStream(dataFile.toString())), controller);
        } catch (IOException e) {
            System.err.println("Error: Cannot read data file.");
            readFile(controller);
        }

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        View view = new View(dataFile, controller);
        view.run(inputReader, outputWriter, command);


    }

    private static void readFile(CollectionController controller) {
        try {
            Scanner scanner = new Scanner(System.in);
            String name = readName(scanner);
            JsonCollectionControllerRepr.read(new InputStreamReader(new FileInputStream(name.toString())), controller);
        } catch (IOException e) {
            System.err.println("Error: Cannot read data file.");
            readFile(controller);
        }
    }

    private static String readName(Scanner scanner) throws IOException {
        while (true) {
            String name;
            System.err.println("Enter path to data file: ");
            while (scanner.hasNextLine()) {
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.err.println("It cannot be empty");
                    continue;
                }
                return name;
            }
        }
    }
}