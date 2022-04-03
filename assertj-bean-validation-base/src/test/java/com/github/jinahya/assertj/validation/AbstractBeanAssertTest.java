package com.github.jinahya.assertj.validation;

public abstract class AbstractBeanAssertTest<
        SELF extends AbstractBeanAssertBase<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssertTest<SELF, ACTUAL, VALIDATOR> {

    protected AbstractBeanAssertTest(final Class<SELF> assertClass) {
        super(assertClass);
    }
}