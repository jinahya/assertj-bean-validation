package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

/**
 * A class for creating bean validation assertion instances.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanValidationAssertions {

    /**
     * Creates a new bean validation assertion instance for specified bean object.
     *
     * @param object the bean object to be verified; must be not {@code null}.
     * @return a new bean validation assertion instance.
     * @see #assertThat(BeanWrapper)
     */
    public static @NotNull BeanValidationAssert assertBean(final @NotNull Object object) {
        requireNonNull(object, "object is null");
        return new BeanValidationAssert(object);
    }

    /**
     * Creates a new bean validation assertion instance for the bean object wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the bean object to be verified; must be not {@code null}.
     * @return a new bean validation assertion instance.
     * @see #assertBean(Object)
     */
    public static BeanValidationAssert assertThat(final @NotNull BeanWrapper wrapper) {
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
