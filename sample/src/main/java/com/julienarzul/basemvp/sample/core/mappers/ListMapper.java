package com.julienarzul.basemvp.sample.core.mappers;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ ZenPark 2016
 */

public class ListMapper<SOURCE, RESULT> implements Mapper<List<SOURCE>, List<RESULT>> {

    private final Mapper<SOURCE, RESULT> mapper;

    public ListMapper(Mapper<SOURCE, RESULT> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<RESULT> map(List<SOURCE> models) {
        List<RESULT> persistences = new ArrayList<>();
        if (models != null && models.size() > 0) {
            for (SOURCE model : models) {
                RESULT mappedObject;
                try {
                    mappedObject = mapper.map(model);
                } catch (Exception e) {
                    if (model != null) {
                        Timber.w(e, "Error mapping object : %s", model);
                    } else {
                        Timber.w(e, "Error trying to map null object");
                    }
                    mappedObject = null;
                }
                if (mappedObject != null) {
                    persistences.add(mappedObject);
                }
            }
        }
        return persistences;
    }
}
