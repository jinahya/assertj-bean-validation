package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionAssertions.assertConstraintDeclarationException;
import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionTestUtils.newConstraintDeclarationException;
import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionWrapper.constraintDeclarationException;

class ConstraintDeclarationExceptionAssertionsTest {

    @Test
    void assertThat__() {
        final RuntimeException actual = newConstraintDeclarationException();
        final ConstraintDeclarationExceptionAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final RuntimeException actual = newConstraintDeclarationException();
        final ConstraintDeclarationExceptionAssert a = assertThat(constraintDeclarationException(actual));
    }

    @Test
    void assertConstraintDeclarationException__() {
        final RuntimeException actual = newConstraintDeclarationException();
        final ConstraintDeclarationExceptionAssert a = assertConstraintDeclarationException(actual);
    }
}