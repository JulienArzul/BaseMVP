package com.julienarzul.basemvp.sample.datasource;

/**
 * Copyright @ Julien Arzul 2016
 */

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static ITasksDatasource tasksDatasource() {
        return TasksDatasource.getInstance();
    }
}
