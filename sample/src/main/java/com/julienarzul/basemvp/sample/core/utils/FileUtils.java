package com.julienarzul.basemvp.sample.core.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Copyright @ Julien Arzul 2017
 */
public class FileUtils {

    private FileUtils() {
    }

    public static <STORAGE> STORAGE readJsonObjectFile(@NonNull File contentFile, Class<STORAGE> storageClass) {
        STORAGE result = null;

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(contentFile));
            Gson gson = new GsonBuilder().create();

            result = gson.fromJson(jsonReader, storageClass);
            jsonReader.close();
        } catch (JsonParseException | IOException e) {
            if (e instanceof FileNotFoundException) {
                Timber.d("File not found : %s", contentFile.getPath());
            } else {
                Timber.e(e, "Impossible to read file %s", contentFile.getPath());
            }
        }

        return result;
    }

    public static <STORAGE> List<STORAGE> readJsonListFile(@NonNull File contentFile, Class<STORAGE> storageClass) {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(contentFile));
            Gson gson = new GsonBuilder().create();

            List<STORAGE> jsonList = new ArrayList<>();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                STORAGE contentObject = gson.fromJson(jsonReader, storageClass);
                jsonList.add(contentObject);
            }
            jsonReader.endArray();
            jsonReader.close();

            return jsonList;
        } catch (JsonParseException | IOException e) {
            if (e instanceof FileNotFoundException) {
                Timber.d("File not found : %s", contentFile.getPath());
            } else {
                Timber.e(e, "Impossible to read file %s", contentFile.getPath());
            }
        }

        return new ArrayList<>();
    }

    public static <STORAGE> void writeJsonObjectFile(@NonNull File contentFile, Class<STORAGE> storageClass, STORAGE jsonObject) {
        try {
            Gson gson = new GsonBuilder().create();
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(contentFile));

            jsonWriter.setIndent("  ");
            gson.toJson(jsonObject, storageClass, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            Timber.e(e, "Impossible to write into file %s\nObject : \n%s", contentFile.getPath(), jsonObject);
        }
    }

    public static <STORAGE> void writeJsonListFile(@NonNull File contentFile, Class<STORAGE> storageClass, List<STORAGE> jsonList) {
        try {
            Gson gson = new GsonBuilder().create();
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(contentFile));

            jsonWriter.setIndent("  ");
            jsonWriter.beginArray();
            for (STORAGE storageObject : jsonList) {
                gson.toJson(storageObject, storageClass, jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException e) {
            Timber.e(e, "Impossible to write into file %s\nList : \n%s", contentFile.getPath(), jsonList);
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
