package com.github.jinahya.assertj.validation;

public class BeanValidationAssertions {

    public static BeanValidationAssert assertBean(final Object actual) {
        return new BeanValidationAssert(actual);
    }

    protected BeanValidationAssertions() {
        super();
    }
}
