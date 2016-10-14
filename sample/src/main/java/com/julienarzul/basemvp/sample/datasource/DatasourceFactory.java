package com.julienarzul.basemvp.sample.datasource;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2AppaZ 2016
 */

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static ITasksDatasource tasksDatasource() {
        return TasksDatasource.getInstance();
    }
}
