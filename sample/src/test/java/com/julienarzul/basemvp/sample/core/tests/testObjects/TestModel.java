package com.julienarzul.basemvp.sample.core.tests.testObjects;

/**
 * Model object for testing purposes
 * Copyright @ Julien Arzul 2017
 */

public class TestModel {

    private final int testInteger;

    private final String testString;

    private TestModel(int testInteger, String testString) {
        this.testInteger = testInteger;
        this.testString = testString;
    }

    public static TestModel create(Integer testInteger, String testString) {
        if (testInteger == null) {
            return null;
        }

        return new TestModel(testInteger, testString);
    }

    public int testInteger() {
        return testInteger;
    }

    public String testString() {
        return testString;
    }

    @Override
    public int hashCode() {
        int result = testInteger;
        result = 31 * result + (testString != null ? testString.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestModel testModel = (TestModel) o;

        if (testInteger != testModel.testInteger) return false;
        return testString != null ? testString.equals(testModel.testString) : testModel.testString == null;

    }
}
