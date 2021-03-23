package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanPropertyValidationAssertions {

    /**
     * Creates a new assert for specified property value.
     *
     * @param value the property value.
     * @return an assert for {@code value}.
     */
    public static BeanPropertyValidationAssert assertBeanProperty(final Object value) {
        return new BeanPropertyValidationAssert(value);
    }

    /**
     * Creates a new assert for the property value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the property value.
     * @return an assert for {@code wrapper.value}.
     */
    public static BeanPropertyValidationAssert assertThat(final BeanPropertyWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBeanProperty(wrapper.getValue());
    }

    /**
     * Creates a new instance.
     */
    protected BeanPropertyValidationAssertions() {
        super();
    }
}
