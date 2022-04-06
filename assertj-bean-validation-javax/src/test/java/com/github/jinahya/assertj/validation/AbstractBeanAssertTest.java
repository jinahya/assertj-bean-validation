package com.github.jinahya.assertj.validation;

import javax.validation.Validator;

public abstract class AbstractBeanAssertTest<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssertTest<SELF, ACTUAL, Validator> {

    protected AbstractBeanAssertTest(final Class<SELF> assertionClass, final Class<ACTUAL> actualClass) {
        super(assertionClass, actualClass, Validator.class);
    }
}