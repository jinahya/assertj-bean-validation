package com.github.jinahya.assertj.validation.jakarta;

import com.github.jinahya.assertj.validation.AbstractWrapper;

import javax.validation.ConstraintViolation;

import static java.util.Objects.requireNonNull;

/**
 * A class for creating assertions for {@link ConstraintViolation}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintViolationAssertions {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @param <T>    the type of the root bean of {@code actual}.
     * @return a new assertion instance for {@code actual}.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(final ConstraintViolation<T> actual) {
        return new ConstraintViolationAssert<>(actual);
    }

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @param <T>    the type of the root bean of {@code actual}.
     * @return a new assertion instance for {@code actual}.
     * @see #assertThat(ConstraintViolation)
     */
    public static <T> ConstraintViolationAssert<T> assertConstraintViolation(final ConstraintViolation<T> actual) {
        return new ConstraintViolationAssert<>(actual);
    }

    /**
     * Creates a new instance for an actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value.
     * @param <T>     the type of the root bean of {@code wrapper.actual}.
     * @return a new assertion instance for {@code wrapper.actual}.
     * @see #assertThat(ConstraintViolation)
     * @see ConstraintViolationWrapper#constraintViolation(ConstraintViolation)
     */
    public static <T> ConstraintViolationAssert<T> assertThat(
            final AbstractWrapper<? extends ConstraintViolation<T>> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertThat(wrapper.getActual());
    }

    private ConstraintViolationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}