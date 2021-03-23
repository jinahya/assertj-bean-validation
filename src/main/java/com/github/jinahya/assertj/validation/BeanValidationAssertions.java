package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanValidationAssertions {

    public static BeanValidationAssert assertBean(final Object object) {
        requireNonNull(object, "object is null");
        return new BeanValidationAssert(object);
    }

    public static BeanValidationAssert assertThat(final BeanWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBean(wrapper.getObject());
    }

    /**
     * Creates a new instance.
     */
    protected BeanValidationAssertions() {
        super();
    }
}
