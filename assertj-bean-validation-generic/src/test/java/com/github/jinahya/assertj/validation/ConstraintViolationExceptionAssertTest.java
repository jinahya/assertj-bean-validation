package com.github.jinahya.assertj.validation;

import org.assertj.core.api.IterableAssert;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionTestUtils.newConstraintViolationException;

class ConstraintViolationExceptionAssertTest {

    @Test
    void constraintViolations__() {
        final RuntimeException actual = newConstraintViolationException();
        final ConstraintViolationExceptionAssert a = new ConstraintViolationExceptionAssert(actual);
        final IterableAssert<Object> constraintViolations = a.constraintViolations();
        constraintViolations.isEmpty();
    }
}