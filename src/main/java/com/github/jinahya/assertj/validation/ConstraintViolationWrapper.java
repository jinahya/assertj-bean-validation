package com.github.jinahya.assertj.validation;

/**
 * A class for wrapping constraint violations.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationWrapper extends Wrapper<Object> {

    /**
     * Creates a new instance wraps specified constraint violation.
     *
     * @param actual the constraint violation to wrap.
     * @return a new instance wraps {@code actual}.
     */
    public static ConstraintViolationWrapper constraintViolation(final Object actual) {
        return new ConstraintViolationWrapper(actual);
    }

    private ConstraintViolationWrapper(final Object wrapped) {
        super(wrapped);
    }
}
