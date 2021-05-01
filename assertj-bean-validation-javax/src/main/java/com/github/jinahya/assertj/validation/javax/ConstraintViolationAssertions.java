package com.github.jinahya.assertj.validation.javax;

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
     * Creates a new instance for an actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value; must not be {@code null}.
     * @param <T>     the type of the root bean of {@code wrapper.actual}.
     * @return a new assertion instance for {@code wrapper.actual}.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(
            final AbstractWrapper<? extends ConstraintViolation<T>> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
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
        return assertThat(actual);
    }

    private ConstraintViolationAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}