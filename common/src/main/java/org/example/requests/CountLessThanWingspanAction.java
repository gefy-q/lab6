/*
Подсчитывает сколько дракончиков имеют меньший размах крыльев, чем введенный. Получает число, возвращает количество
 */

package org.example.requests;

import org.example.utily.Actions;

public class CountLessThanWingspanAction extends Action {
    private final int count;

    public CountLessThanWingspanAction(int count) {
        super(Actions.COUNT_LESS_THAN_WINGSPAN);
        this.count = count;
    }

}
