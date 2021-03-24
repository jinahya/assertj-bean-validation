package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

/**
 * An assertion class for validating a value for a property of a bean type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanPropertyValidationAssertions {

    /**
     * Creates a new assertion instance with specified property value.
     *
     * @param value the property value.
     * @return an assertion instance for {@code value}.
     * @see #assertThat(BeanPropertyWrapper)
     */
    public static @NotNull BeanPropertyValidationAssert assertBeanProperty(final Object value) {
        return new BeanPropertyValidationAssert(value);
    }

    /**
     * Creates a new assertion instance for the property value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the property value.
     * @return an assert for {@code wrapper.value}.
     * @see #assertBeanProperty(Object)
     */
    public static @NotNull BeanPropertyValidationAssert assertThat(final @NotNull BeanPropertyWrapper wrapper) {
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
