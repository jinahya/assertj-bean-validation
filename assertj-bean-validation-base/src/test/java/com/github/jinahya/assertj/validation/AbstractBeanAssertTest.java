package com.github.jinahya.assertj.validation;

public abstract class AbstractBeanAssertTest<
        SELF extends AbstractBeanAssertBase<SELF, VALIDATOR, CONSTRAINT_VIOLATION>,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssertTest<SELF, VALIDATOR> {

    protected AbstractBeanAssertTest(final Class<SELF> assertClass) {
        super(assertClass);
    }
}