package com.github.jinahya.assertj.validation;

import java.util.Objects;

abstract class ValidationAssertTest<SELF extends ValidationAssert<SELF, ACTUAL, VALIDATOR>, ACTUAL, VALIDATOR> {

    protected ValidationAssertTest(final Class<SELF> assertClass, final Class<ACTUAL> actualClass,
                                   final Class<VALIDATOR> validatorClass) {
        super();
        this.assertClass = Objects.requireNonNull(assertClass, "assertClass is null");
        this.actualClass = Objects.requireNonNull(actualClass, "actualClass is null");
        this.validatorClass = Objects.requireNonNull(validatorClass, "validatorClass is null");
    }

    protected final Class<SELF> assertClass;

    protected final Class<ACTUAL> actualClass;

    protected final Class<VALIDATOR> validatorClass;
}