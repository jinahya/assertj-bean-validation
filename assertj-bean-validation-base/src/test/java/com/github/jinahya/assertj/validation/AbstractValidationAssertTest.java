package com.github.jinahya.assertj.validation;

abstract class AbstractValidationAssertTest<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends ValidationAssertTest<SELF, ACTUAL, VALIDATOR> {

    protected AbstractValidationAssertTest(final Class<SELF> assertClass, final Class<ACTUAL> actualClass,
                                           final Class<VALIDATOR> validatorClass) {
        super(assertClass, actualClass, validatorClass);
    }
}