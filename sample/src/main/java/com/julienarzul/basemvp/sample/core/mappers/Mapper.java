package com.julienarzul.basemvp.sample.core.mappers;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ ZenPark 2016
 */

public interface Mapper<SOURCE, RESULT> {

    RESULT map(SOURCE source);
}
