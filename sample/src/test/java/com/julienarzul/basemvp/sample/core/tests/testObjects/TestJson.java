package com.julienarzul.basemvp.sample.core.tests.testObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Json storing class for testing purposes
 * <p>
 * Copyright @ Julien Arzul 2017
 */

public class TestJson {

    @SerializedName("testInt")
    @Expose
    public Integer testInteger;
    @SerializedName("testString")
    @Expose
    public String testString;
}
