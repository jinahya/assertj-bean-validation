package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public abstract class AbstractValidationAssertTest<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR> {

    protected AbstractValidationAssertTest(final Class<SELF> assertClass) {
        super();
        this.assertClass = requireNonNull(assertClass, "assertClass is null");
    }

    /**
     * The class of {@link SELF} to test.
     */
    protected Class<SELF> assertClass;
}