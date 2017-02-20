package com.julienarzul.basemvp.sample.core.datasources;

import android.content.Context;

import com.julienarzul.basemvp.sample.core.repositories.storage.FileStorageRepository;
import com.julienarzul.basemvp.sample.core.repositories.storage.JsonFileStorageManager;

/**
 * Copyright @ Julien Arzul 2016
 */

public class DatasourceFactory {

    private volatile static ITasksDatasource tasksDatasource;

    private DatasourceFactory() {
    }

    public static ITasksDatasource tasksDatasource(Context context) {
        if (tasksDatasource == null) {
            synchronized (TasksDatasource.class) {
                tasksDatasource = new TasksDatasource(new FileStorageRepository(new JsonFileStorageManager(context)));
            }
        }

        return tasksDatasource;
    }
}
