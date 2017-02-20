package com.julienarzul.basemvp.sample.core.mappers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class ListMapperTest {

    /**
     * Input:
     * A List of String from "0" to "9"
     * [ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" ]
     * <p/>
     * Expected Output:
     * A List of Integer from 0 to 9
     * [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
     */
    @Test
    public void testListMapperWithWellFormedSource() {
        Mapper<List<String>, List<Integer>> testedMapper = new ListMapper<>(new StringToIntMapper());

        List<String> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(String.valueOf(i));
        }

        List<Integer> result = testedMapper.map(source);

        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedResult.add(i);
        }

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Input:
     * null
     * <p/>
     * Expected Output:
     * An empty List
     * [ ]
     */
    @Test
    public void testListMapperWithNullSource() {
        Mapper<List<String>, List<Integer>> testedMapper = new ListMapper<>(new StringToIntMapper());

        List<String> source = null;

        List<Integer> result = testedMapper.map(source);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    /**
     * Input:
     * An empty list
     * [ ]
     * <p/>
     * Expected Output:
     * An empty List
     * [ ]
     */
    @Test
    public void testListMapperWithEmptySource() {
        Mapper<List<String>, List<Integer>> testedMapper = new ListMapper<>(new StringToIntMapper());

        List<String> source = new ArrayList<>();

        List<Integer> result = testedMapper.map(source);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    /**
     * Input:
     * A List of String from "0" to "9" with null elements
     * [ "0", "1", "2", null, "3", "4", "5", null, "6", "7", "8", "9" ]
     * <p/>
     * Expected Output:
     * A List of Integer from 0 to 9
     * [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
     */
    @Test
    public void testListMapperWithNullElementsInSource() {
        Mapper<List<String>, List<Integer>> testedMapper = new ListMapper<>(new StringToIntMapper());

        List<String> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(String.valueOf(i));
        }

        source.add(3, null);
        source.add(7, null);

        List<Integer> result = testedMapper.map(source);

        // Expected list is still number from 0 to 10 -- 3rd and 7th index are null and ignored
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedResult.add(i);
        }

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Input:
     * A List of String from "0" to "9" with string elements that can't be converted to Integer
     * [ "0", "1", "2", "Lorem", "3", "4", "5", "Ipsum", "6", "7", "8", "9" ]
     * <p/>
     * Expected Output:
     * A List of Integer from 0 to 9
     * [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
     */
    @Test
    public void testListMapperWithUnmappableElementsInSource() {
        Mapper<List<String>, List<Integer>> testedMapper = new ListMapper<>(new StringToIntMapper());

        List<String> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(String.valueOf(i));
        }

        source.add(3, "Lorem");
        source.add(7, "Ipsum");

        List<Integer> result = testedMapper.map(source);

        // Expected list is still number from 0 to 10 -- 3rd and 7th index can not be mapped to Integer and ignored
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedResult.add(i);
        }

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Simple Mapper from Integer to String for testing purposes
     */
    private static class StringToIntMapper implements Mapper<String, Integer> {

        @Override
        public Integer map(String s) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
