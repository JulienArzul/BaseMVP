package com.julienarzul.basemvp.sample.datasource;

import com.julienarzul.basemvp.sample.model.DatasourceError;

/**
 * Created by Julien Arzul on 7/2/17.
 * Copyright @ ZenPark 2017
 */
public interface DataCallback<T> {

    void onDataLoaded(T data);

    void onDataError(DatasourceError error);
}
