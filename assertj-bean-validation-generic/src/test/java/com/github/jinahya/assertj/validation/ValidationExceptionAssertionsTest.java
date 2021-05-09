package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ValidationExceptionAssertions.assertValidationException;
import static com.github.jinahya.assertj.validation.ValidationExceptionTestUtils.newValidationException;
import static com.github.jinahya.assertj.validation.ValidationExceptionWrapper.validationException;

class ValidationExceptionAssertionsTest {

    @Test
    void assertThat__() {
        final RuntimeException actual = newValidationException();
        final ValidationExceptionAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final RuntimeException actual = newValidationException();
        final ValidationExceptionWrapper w = validationException(actual);
        final ValidationExceptionAssert a = assertThat(w);
    }

    @Test
    void assertValidationException__() {
        final RuntimeException actual = newValidationException();
        final ValidationExceptionAssert a = assertValidationException(actual);
    }
}