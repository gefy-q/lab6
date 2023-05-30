/*
Получает число и считает, сколько дракончиков ему соответствует, отдает ответ
*/
package org.example.requests;

import org.example.utily.Actions;

public class CountByAgeAction extends Action {
    private final int age;

    public CountByAgeAction(int age) {
        super(Actions.COUNT_BY_AGE);
        this.age = age;
    }
}
