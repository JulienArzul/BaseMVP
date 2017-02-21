package com.julienarzul.basemvp.sample.core.repositories.storage;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.julienarzul.basemvp.sample.core.mappers.ListMapper;
import com.julienarzul.basemvp.sample.core.mappers.Mapper;
import com.julienarzul.basemvp.sample.core.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class JsonFileStorageManager {

    private static final String STORAGE_SUBDIRECTORY = "storage";

    private final File storageFileDir;

    public JsonFileStorageManager(File storageFileDirectory) {
        this.storageFileDir = new File(storageFileDirectory, STORAGE_SUBDIRECTORY);

        if (!this.storageFileDir.exists()) {
            this.storageFileDir.mkdir();
        }
    }

    public <MODEL, STORAGE> MODEL readObject(String storageKey, Class<STORAGE> storageTypeClass, Mapper<STORAGE, MODEL> mapper) {
        if (TextUtils.isEmpty(storageKey) || storageTypeClass == null || mapper == null) {
            return null;
        }

        File storageFile = this.getFileForKey(storageKey);

        STORAGE storageObject = FileUtils.readJsonObjectFile(storageFile, storageTypeClass);
        return mapper.map(storageObject);
    }

    public <MODEL, STORAGE> List<MODEL> readObjectList(String storageKey, Class<STORAGE> storageTypeClass, Mapper<STORAGE, MODEL> mapper) {
        if (TextUtils.isEmpty(storageKey) || storageTypeClass == null || mapper == null) {
            return null;
        }

        File storageFile = this.getFileForKey(storageKey);
        if (!storageFile.exists()) {
            return null;
        }

        List<STORAGE> storageList = FileUtils.readJsonListFile(storageFile, storageTypeClass);
        ListMapper<STORAGE, MODEL> listMapper = new ListMapper<>(mapper);

        return listMapper.map(storageList);
    }

    public <MODEL, STORAGE> void writeObject(String storageKey, MODEL object, Class<STORAGE> storageTypeClass, Mapper<MODEL, STORAGE> mapper) {
        if (TextUtils.isEmpty(storageKey) || storageTypeClass == null || mapper == null) {
            return;
        }

        File storageFile = this.getFileForKey(storageKey);
        STORAGE storageObject = mapper.map(object);

        if (storageObject == null) {
            FileUtils.deleteFile(storageFile);
        } else {
            FileUtils.writeJsonObjectFile(storageFile, storageTypeClass, storageObject);
        }
    }

    public <MODEL, STORAGE> void writeObjectList(String storageKey, List<MODEL> objectList, Class<STORAGE> storageTypeClass, Mapper<MODEL, STORAGE> mapper) {
        if (TextUtils.isEmpty(storageKey) || storageTypeClass == null || mapper == null) {
            return;
        }

        File storageFile = this.getFileForKey(storageKey);

        if (objectList == null) {
            FileUtils.deleteFile(storageFile);
        } else {
            ListMapper<MODEL, STORAGE> listMapper = new ListMapper<>(mapper);
            List<STORAGE> storageList = listMapper.map(objectList);

            FileUtils.writeJsonListFile(storageFile, storageTypeClass, storageList);
        }
    }

    @NonNull
    private File getFileForKey(String storageKey) {
        return new File(this.storageFileDir, storageKey + ".json");
    }
}
