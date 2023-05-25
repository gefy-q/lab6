/*
Для того, чтобы вывод постоянно не писать такой большой
 */

package org.example.src.representations.console;

import org.example.src.menu.actions.Action;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ConsoleRepr {
    protected static void print(Writer writer, String str) throws IOException {
        writer.write(str);
        writer.flush();
    }

    protected static void println(Writer writer, String str) throws IOException {
        writer.write(str);
        writer.write(System.lineSeparator());
        writer.flush();
    }
}
