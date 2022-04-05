package com.github.jinahya.assertj.validation;

import javax.validation.Validator;

public abstract class AbstractValueAssertTest<SELF extends AbstractValueAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssertTest<SELF, ACTUAL, Validator> {

    protected AbstractValueAssertTest(final Class<SELF> assertionClass, final Class<ACTUAL> actualClass) {
        super(assertionClass, actualClass, Validator.class);
    }
}