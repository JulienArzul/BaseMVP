package com.julienarzul.basemvp.sample.core.tests.testObjects;

import com.julienarzul.basemvp.sample.core.mappers.Mapper;

/**
 * Mapper for testing purposes
 * <p>
 * Copyright @ Julien Arzul 2017
 */
public class TestMapper {

    public static Mapper<TestJson, TestModel> regularMapper() {
        return new RegularMapper();
    }

    public static Mapper<TestModel, TestJson> inverseMapper() {
        return new InverseMapper();
    }

    private static class RegularMapper implements Mapper<TestJson, TestModel> {

        @Override
        public TestModel map(TestJson source) {
            if (source == null) {
                return null;
            }

            return TestModel.create(source.testInteger, source.testString);
        }
    }

    private static class InverseMapper implements Mapper<TestModel, TestJson> {

        @Override
        public TestJson map(TestModel source) {
            if (source == null) {
                return null;
            }

            TestJson result = new TestJson();
            result.testInteger = source.testInteger();
            result.testString = source.testString();

            return result;
        }
    }
}
