package com.julienarzul.basemvp.sample.core.repositories.storage;

import com.julienarzul.basemvp.sample.core.mappers.Mapper;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public interface IStorageManager {

    <MODEL, STORAGE> MODEL readObject(String storageKey, Class<STORAGE> storageTypeClass, Mapper<STORAGE, MODEL> mapper);

    <MODEL, STORAGE> List<MODEL> readObjectList(String storageKey, Class<STORAGE> storageTypeClass, Mapper<STORAGE, MODEL> mapper);

    <MODEL, STORAGE> void writeObject(String storageKey, MODEL object, Class<STORAGE> storageTypeClass, Mapper<MODEL, STORAGE> mapper);

    <MODEL, STORAGE> void writeObjectList(String storageKey, List<MODEL> objectList, Class<STORAGE> storageTypeClass, Mapper<MODEL, STORAGE> mapper);
}
