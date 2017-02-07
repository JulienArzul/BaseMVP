package com.julienarzul.basemvp.sample.datasource;

import com.julienarzul.basemvp.sample.model.Task;

import java.util.List;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2AppaZ 2016
 */

public interface ITasksDatasource {

    void getTaskList(DataCallback<List<Task>> callback);
}
