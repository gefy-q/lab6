/*
Выводит элементы коллекции, сначала часть, потом можно выбрать, что именно нужно узнать
 */
package org.example.responses;

import org.example.src.model.Dragon;
import org.example.udpclient.utily.Actions;

import java.util.List;

public class ShowAction extends Action {
    private final List<Dragon> dragons;

    public ShowAction(List<Dragon> dragons, String error) {
        super(Actions.SHOW, error);
        this.dragons = dragons;
    }

}
