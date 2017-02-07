package com.julienarzul.basemvp.sample.datasource;

import com.julienarzul.basemvp.sample.model.DatasourceError;

/**
 * Copyright @ Julien Arzul 2017
 */
public interface DataCallback<T> {

    void onDataLoaded(T data);

    void onDataError(DatasourceError error);
}
