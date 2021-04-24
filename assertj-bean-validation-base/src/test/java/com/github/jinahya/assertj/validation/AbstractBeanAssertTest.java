package com.github.jinahya.assertj.validation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"java:S119"})
public abstract class AbstractBeanAssertTest<
        ASSERT extends AbstractBeanAssert<ASSERT, Object, VALIDATOR, CONSTRAINT_VIOLATION>,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssertTest<ASSERT, Object, VALIDATOR> {

    protected AbstractBeanAssertTest(final Class<ASSERT> assertClass) {
        super(assertClass, Object.class);
    }
}