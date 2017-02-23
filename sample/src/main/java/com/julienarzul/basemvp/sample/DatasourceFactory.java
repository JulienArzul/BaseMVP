package com.julienarzul.basemvp.sample;

import com.julienarzul.basemvp.sample.core.datasources.EventDatasource;
import com.julienarzul.basemvp.sample.core.datasources.IEventDatasource;

/**
 * Copyright @ Julien Arzul 2017
 */

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static IEventDatasource eventDatasource() {
        return new EventDatasource();
    }
}
