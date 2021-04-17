package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;

/**
 * A class for wrapping instances of {@link ConstraintViolation}.
 *
 * @param <T> the type of the root bean.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintViolationWrapper<T>
        extends AbstractWrapper<ConstraintViolation<T>> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     * @param <T>    the type of the root bean of the {@code actual}.
     * @return a new instance wraps {@code actual}.
     * @see ConstraintViolationAssertions#assertThat(AbstractWrapper)
     */
    public static <T> ConstraintViolationWrapper<T> constraintViolation(final ConstraintViolation<T> actual) {
        return new ConstraintViolationWrapper<>(actual);
    }

    private ConstraintViolationWrapper(final ConstraintViolation<T> actual) {
        super(actual);
    }
}
