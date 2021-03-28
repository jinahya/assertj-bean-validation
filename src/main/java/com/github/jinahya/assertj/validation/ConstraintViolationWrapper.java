package com.github.jinahya.assertj.validation;

public class ConstraintViolationWrapper extends Wrapper<Object> {

    public static ConstraintViolationWrapper constraintViolation(final Object actual) {
        return new ConstraintViolationWrapper(actual);
    }

    private ConstraintViolationWrapper(final Object wrapped) {
        super(wrapped);
    }
}
