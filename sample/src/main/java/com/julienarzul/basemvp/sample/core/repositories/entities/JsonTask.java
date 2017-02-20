package com.julienarzul.basemvp.sample.core.repositories.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright @ Julien Arzul 2017
 */
public class JsonTask {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("description")
    @Expose
    public String description;
}
