package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

/**
 * An assertion class for verifying instances of {@link ConstraintViolation}.
 *
 * @param <T> the type of the root bean
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<T>, ConstraintViolation<T>, Path, T> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify
     * @see #actual
     */
    public ConstraintViolationAssert(final ConstraintViolation<T> actual) {
        super(actual, ConstraintViolationAssert.class);
    }

    @Override
    Object getInvalidValue(final ConstraintViolation<T> actual) {
        return actual.getInvalidValue();
    }

    @Override
    Object getLeafBean(final ConstraintViolation<T> actual) {
        return actual.getLeafBean();
    }

    @Override
    String getMessage(final ConstraintViolation<T> actual) {
        return actual.getMessage();
    }

    @Override
    Path getPropertyPath(final ConstraintViolation<T> actual) {
        return actual.getPropertyPath();
    }

    @Override
    T getRootBean(final ConstraintViolation<T> actual) {
        return actual.getRootBean();
    }

    @Override
    Class<T> getRootBeanClass(final ConstraintViolation<T> actual) {
        return actual.getRootBeanClass();
    }
}
