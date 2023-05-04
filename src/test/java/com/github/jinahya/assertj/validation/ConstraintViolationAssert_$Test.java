package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;

abstract class ConstraintViolationAssert_$Test {

    <T> ConstraintViolationAssert<?, ConstraintViolation<T>, T> assertion(final ConstraintViolation<T> actual) {
        return new DefaultConstraintViolationAssert<>(actual);
    }
}
