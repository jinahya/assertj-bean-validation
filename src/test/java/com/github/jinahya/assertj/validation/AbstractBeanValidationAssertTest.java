package com.github.jinahya.assertj.validation;

import java.util.Objects;

class AbstractBeanValidationAssertTest<T extends AbstractBeanValidationAssert<T>> {

    AbstractBeanValidationAssertTest(final Class<T> assertClass) {
        super();
        this.assertClass = Objects.requireNonNull(assertClass, "assertClass is null");
    }

    protected final Class<T> assertClass;
}