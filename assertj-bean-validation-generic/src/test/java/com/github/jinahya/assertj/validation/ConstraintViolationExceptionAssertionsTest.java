package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionAssertions.assertConstraintViolationException;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionTestUtils.newConstraintViolationException;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionWrapper.constraintViolationException;

class ConstraintViolationExceptionAssertionsTest {

    @Test
    void assertThat__() {
        final RuntimeException actual = newConstraintViolationException();
        final ConstraintViolationExceptionAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final RuntimeException actual = newConstraintViolationException();
        final ConstraintViolationExceptionAssert a = assertThat(constraintViolationException(actual));
    }

    @Test
    void assertConstraintViolationException__() {
        final RuntimeException actual = newConstraintViolationException();
        final ConstraintViolationExceptionAssert a = assertConstraintViolationException(actual);
    }
}