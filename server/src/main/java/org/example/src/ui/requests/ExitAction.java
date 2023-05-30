/*
Для выхода из программы
 */

package org.example.src.ui.requests;

import java.io.Writer;
import java.util.Scanner;

public class ExitAction extends Action {
    public ExitAction(String args, String description) {
        super(args, description);
    }

    @Override
    public boolean process(Scanner scanner, Writer writer) {
        return false;
    }
}
