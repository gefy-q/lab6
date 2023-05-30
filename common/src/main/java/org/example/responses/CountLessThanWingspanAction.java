/*
Подсчитывает сколько дракончиков имеют меньший размах крыльев, чем введенный. Получает число, возвращает количество
 */

package org.example.responses;

import org.example.utily.Actions;

public class CountLessThanWingspanAction extends Action {
    private final int count;

    public CountLessThanWingspanAction(int count, String error) {
        super(Actions.COUNT_LESS_THAN_WINGSPAN, error);
        this.count = count;
    }
}
