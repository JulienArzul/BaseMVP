package com.julienarzul.basemvp.sample.taskList;

import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.model.Task;

import java.util.List;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2appaZ 2016
 */

interface TaskListContract {

    interface View extends MvpContract.View {

        void setTaskList(List<Task> data);
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void loadData();
    }
}
