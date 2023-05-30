/*
Получает число и считает, сколько дракончиков ему соответствует, отдает ответ
*/
package org.example.responses;

import org.example.utily.Actions;

public class CountByAgeAction extends Action {
    private final int age;

    public CountByAgeAction(int age, String error) {
        super(Actions.COUNT_BY_AGE, error);
        this.age = age;
    }
}
