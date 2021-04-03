package com.github.jinahya.assertj.validation;

import java.util.function.Function;

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.applyConstraintViolationClassFor;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.requireConstraintViolationInstance;

/**
 * An assertion class for verifying an instance of {@code ConstraintViolation}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<T>, Object, T> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @see #actual
     */
    public ConstraintViolationAssert(final Object actual) {
        super(requireConstraintViolationInstance(actual), ConstraintViolationAssert.class,
              applyConstraintViolationClassFor(actual, Function.identity()));
    }
}
