package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotNull;

/**
 * A class for wrapping a property value.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BeanPropertyValidationAssertions#assertThat(BeanPropertyWrapper)
 */
public class BeanPropertyWrapper {

    /**
     * Creates a new instance wraps specified property value.
     *
     * @param value the property value to wrap.
     * @return a new instance wraps {@code value}.
     */
    public static @NotNull BeanPropertyWrapper beanProperty(final Object value) {
        return new BeanPropertyWrapper(value);
    }

    /**
     * Creates a new instance with specified property value.
     *
     * @param value the property value to wrap.
     */
    private BeanPropertyWrapper(final Object value) {
        super();
        this.value = value;
    }

    /**
     * Returns the wrapped property value.
     *
     * @return the wrapped property value.
     */
    Object getValue() {
        return value;
    }

    /**
     * The wrapped property value.
     */
    private final Object value;
}
