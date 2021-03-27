package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public final class ConstraintViolationAssertions {

    public static class ConstraintViolationWrapper extends Wrapper<Object> {

        public static ConstraintViolationWrapper constraintViolation(final Object actual) {
            return new ConstraintViolationWrapper(actual);
        }

        private ConstraintViolationWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static ConstraintViolationAssert assertConstraintViolation(final Object actual) {
        return new ConstraintViolationAssert(actual);
    }

    public static ConstraintViolationAssert assertThat(final ConstraintViolationWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertConstraintViolation(wrapper.getWrapped());
    }

    private ConstraintViolationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
