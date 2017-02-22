package com.julienarzul.basemvp.sample.core.utils;

import com.google.common.io.Files;
import com.julienarzul.basemvp.sample.core.tests.testObjects.TestJson;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
public class FileUtilsTest {

    private static final int JSON_LIST_SIZE = 4;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testReadJsonObjectFile() throws Exception {
        File jsonFile = new File(getClass().getClassLoader().getResource("testObject.json").getFile());
        TestJson readObject = FileUtils.readJsonObjectFile(jsonFile, TestJson.class);

        Assert.assertEquals(Integer.valueOf(11), readObject.testInteger);
        Assert.assertEquals("Test 11", readObject.testString);
    }

    @Test
    public void testReadJsonListFile() throws Exception {
        File jsonFile = new File(getClass().getClassLoader().getResource("testList.json").getFile());
        List<TestJson> readList = FileUtils.readJsonListFile(jsonFile, TestJson.class);

        int expectedSizeList = JSON_LIST_SIZE;
        Assert.assertEquals(expectedSizeList, readList.size());

        for (int i = 0; i < expectedSizeList; i++) {
            TestJson readObject = readList.get(i);

            Assert.assertEquals(Integer.valueOf(i), readObject.testInteger);
            Assert.assertEquals("Test " + String.valueOf(i), readObject.testString);
        }
    }

    @Test
    public void testWriteJsonObjectFile() throws Exception {
        TestJson objectToWrite = new TestJson();
        objectToWrite.testInteger = 11;
        objectToWrite.testString = "Test 11";

        File writtenFile = this.temporaryFolder.newFile();
        FileUtils.writeJsonObjectFile(writtenFile, TestJson.class, objectToWrite);

        File jsonResFile = new File(getClass().getClassLoader().getResource("testObject.json").getFile());

        Assert.assertTrue(Files.equal(writtenFile, jsonResFile));
    }

    @Test
    public void testWriteJsonListFile() throws Exception {
        int listSize = JSON_LIST_SIZE;
        List<TestJson> listToWrite = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            TestJson objectToWrite = new TestJson();
            objectToWrite.testInteger = i;
            objectToWrite.testString = "Test " + String.valueOf(i);

            listToWrite.add(objectToWrite);
        }

        File writtenFile = this.temporaryFolder.newFile();
        FileUtils.writeJsonListFile(writtenFile, TestJson.class, listToWrite);

        File jsonResFile = new File(getClass().getClassLoader().getResource("testList.json").getFile());

        Assert.assertTrue(Files.equal(writtenFile, jsonResFile));
    }
}