package com.julienarzul.basemvp.sample.core.repositories.storage;

import com.julienarzul.basemvp.sample.core.tests.testObjects.TestJson;
import com.julienarzul.basemvp.sample.core.tests.testObjects.TestMapper;
import com.julienarzul.basemvp.sample.core.tests.testObjects.TestModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
public class JsonFileStorageManagerTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private JsonFileStorageManager testedJsonFileStorageManager;
    private File storageFileDirectory;

    @Before
    public void setUp() throws Exception {
        this.storageFileDirectory = this.temporaryFolder.newFolder();
        this.testedJsonFileStorageManager = new JsonFileStorageManager(this.storageFileDirectory);
    }

    @After
    public void tearDown() throws Exception {
        this.storageFileDirectory.delete();
    }

    @Test
    public void testReadWriteObject() throws Exception {
        TestModel objectTestModel = TestModel.create(0, "0");

        String testObjectKey = "TEST_OBJECT_KEY";
        Class<TestJson> testStorageClass = TestJson.class;

        this.testedJsonFileStorageManager.writeObject(testObjectKey, objectTestModel, testStorageClass, TestMapper.inverseMapper());

        TestModel readObject = this.testedJsonFileStorageManager.readObject(testObjectKey, testStorageClass, TestMapper.regularMapper());

        Assert.assertEquals(objectTestModel, readObject);
    }

    @Test
    public void testReadWriteObjectList() throws Exception {
        List<TestModel> listTestModel = new ArrayList<>();
        listTestModel.add(TestModel.create(0, "0"));
        listTestModel.add(TestModel.create(1, "1"));
        listTestModel.add(TestModel.create(2, "2"));

        String testListKey = "TEST_LIST_KEY";
        Class<TestJson> testStorageClass = TestJson.class;

        this.testedJsonFileStorageManager.writeObjectList(testListKey, listTestModel, testStorageClass, TestMapper.inverseMapper());

        List<TestModel> readList = this.testedJsonFileStorageManager.readObjectList(testListKey, testStorageClass, TestMapper.regularMapper());

        Assert.assertEquals(listTestModel, readList);
    }

}