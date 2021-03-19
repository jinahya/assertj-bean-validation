package com.github.jinahya.assertj.validation;

public class BeanValidationAssertions {

    public static BeanValidationAssert assertBean(final Object actual) {
        return new BeanValidationAssert(actual);
    }

    public static BeanValidationAssert assertThat(final BeanWrapper<?> actual) {
        return assertBean(actual.object);
    }

    protected BeanValidationAssertions() {
        super();
    }
}
