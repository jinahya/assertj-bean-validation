package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanValidationAssertions {

    public static BeanValidationAssert assertBean(final Object object) {
        return new BeanValidationAssert(object);
    }

    public static BeanValidationAssert assertThat(final BeanWrapper<?> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBean(wrapper.object);
    }

    public static BeanValidationAssert assertBeanProperty(final Object object, final String propertyName) {
        return new BeanValidationAssert(object);
    }

    public static BeanValidationAssert assertThat(final BeanPropertyWrapper<?> actual) {
        return assertBeanProperty(actual.object, actual.propertyName);
    }

    protected BeanValidationAssertions() {
        super();
    }
}
