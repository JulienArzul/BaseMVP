package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.DatasourceError;

/**
 * Copyright @ Julien Arzul 2017
 */
public interface DataCallback<T> {

    void onDataLoaded(T data);

    void onDataError(DatasourceError error);
}
