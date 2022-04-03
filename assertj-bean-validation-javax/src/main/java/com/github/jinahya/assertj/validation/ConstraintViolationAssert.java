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
     */
    public ConstraintViolationAssert(final ConstraintViolation<T> actual) {
        super(actual, ConstraintViolationAssert.class);
    }

    @Override
    protected Object getInvalidValue(final ConstraintViolation<T> actual) {
        return actual.getInvalidValue();
    }

    @Override
    protected Object getLeafBean(final ConstraintViolation<T> actual) {
        return actual.getLeafBean();
    }

    @Override
    protected String getMessage(final ConstraintViolation<T> actual) {
        return actual.getMessage();
    }

    @Override
    protected Path getPropertyPath(final ConstraintViolation<T> actual) {
        return actual.getPropertyPath();
    }

    @Override
    protected T getRootBean(final ConstraintViolation<T> actual) {
        return actual.getRootBean();
    }

    @Override
    protected Class<T> getRootBeanClass(final ConstraintViolation<T> actual) {
        return actual.getRootBeanClass();
    }
}
