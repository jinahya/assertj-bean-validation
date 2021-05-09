package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionAssertions.assertConstraintDefinitionException;
import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionTestUtils.newConstraintDefinitionException;
import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionWrapper.constraintDefinitionException;

class ConstraintDefinitionExceptionAssertionsTest {

    @Test
    void assertThat__() {
        final RuntimeException actual = newConstraintDefinitionException();
        final ConstraintDefinitionExceptionAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final RuntimeException actual = newConstraintDefinitionException();
        final ConstraintDefinitionExceptionAssert a = assertThat(constraintDefinitionException(actual));
    }

    @Test
    void assertConstraintDefinitionException__() {
        final RuntimeException actual = newConstraintDefinitionException();
        final ConstraintDefinitionExceptionAssert a = assertConstraintDefinitionException(actual);
    }
}