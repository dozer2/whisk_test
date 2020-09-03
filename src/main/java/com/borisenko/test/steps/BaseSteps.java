package com.borisenko.test.steps;

import com.borisenko.test.util.selenium.DriverManager;

/**
 * Created by maxim on 21.04.18.
 */
public abstract class BaseSteps<T extends BaseSteps> {

    DriverManager driverManager= DriverManager.getInstance();

    public abstract T refreshPage();

}
