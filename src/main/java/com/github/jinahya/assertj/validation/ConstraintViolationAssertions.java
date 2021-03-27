package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public final class ConstraintViolationAssertions {

    public static class ConstraintViolationWrapper {

        public static ConstraintViolationWrapper constraintViolation(final Object actual) {
            return new ConstraintViolationWrapper(actual);
        }

        private ConstraintViolationWrapper(final Object actual) {
            super();
            this.actual = actual;
        }

        private final Object actual;
    }

    public static ConstraintViolationAssert assertConstraintViolation(final Object actual) {
        return new ConstraintViolationAssert(actual);
    }

    public static ConstraintViolationAssert assertThat(final ConstraintViolationWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertConstraintViolation(wrapper.actual);
    }

    private ConstraintViolationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
