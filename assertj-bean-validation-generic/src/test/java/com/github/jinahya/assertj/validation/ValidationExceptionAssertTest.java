package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionTestUtils.newConstraintDeclarationException;
import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionTestUtils.newConstraintDefinitionException;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionTestUtils.newConstraintViolationException;
import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionTestUtils.newGroupDefinitionException;
import static org.assertj.core.api.Assertions.assertThatCode;

class ValidationExceptionAssertTest
        extends AbstractValidationExceptionAssertTest
                        <ValidationExceptionAssert, ValidationExceptionAssert, RuntimeException> {

    ValidationExceptionAssertTest() {
        super(ValidationExceptionAssert.class);
    }

    // ---------------------------------------------------------------------------------- ConstraintDeclarationException
    @DisplayName("ValidationException(ConstraintDeclarationException).isConstraintDeclarationException() passes")
    @Test
    void isConstraintDeclarationException_Pass_ConstraintDeclarationException() {
        final RuntimeException actual = newConstraintDeclarationException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        assertThatCode(a::isConstraintDeclarationException)
                .doesNotThrowAnyException();
    }

    @DisplayName("ValidationException(ConstraintDeclarationException).asConstraintDeclarationException() passes")
    @Test
    void asConstraintDeclarationException_Pass_ConstraintDeclarationException() {
        final RuntimeException actual = newConstraintDeclarationException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        final ConstraintDeclarationExceptionAssert as = a.asConstraintDeclarationException();
    }

    // ---------------------------------------------------------------------------------- ConstraintDefinitionException
    @DisplayName("ValidationException(ConstraintDefinitionException).isConstraintDefinitionException() passes")
    @Test
    void isConstraintDefinitionException_Pass_ConstraintDefinitionException() {
        final RuntimeException actual = newConstraintDefinitionException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        assertThatCode(a::isConstraintDefinitionException)
                .doesNotThrowAnyException();
    }

    @DisplayName("ValidationException(ConstraintDefinitionException).asConstraintDefinitionException() passes")
    @Test
    void asConstraintDefinitionException_Pass_ConstraintDefinitionException() {
        final RuntimeException actual = newConstraintDefinitionException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        final ConstraintDefinitionExceptionAssert as = a.asConstraintDefinitionException();
    }

    // ---------------------------------------------------------------------------------- ConstraintViolationException
    @DisplayName("ValidationException(ConstraintViolationException).isConstraintViolationException() passes")
    @Test
    void isConstraintViolationException_Pass_ConstraintViolationException() {
        final RuntimeException actual = newConstraintViolationException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        assertThatCode(a::isConstraintViolationException)
                .doesNotThrowAnyException();
    }

    @DisplayName("ValidationException(ConstraintViolationException).asConstraintViolationException() passes")
    @Test
    void asConstraintViolationException_Pass_ConstraintViolationException() {
        final RuntimeException actual = newConstraintViolationException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        final ConstraintViolationExceptionAssert as = a.asConstraintViolationException();
    }

    // ---------------------------------------------------------------------------------- GroupDefinitionException
    @DisplayName("ValidationException(GroupDefinitionException).isGroupDefinitionException() passes")
    @Test
    void isGroupDefinitionException_Pass_GroupDefinitionException() {
        final RuntimeException actual = newGroupDefinitionException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        assertThatCode(a::isGroupDefinitionException)
                .doesNotThrowAnyException();
    }

    @DisplayName("ValidationException(GroupDefinitionException).asGroupDefinitionException() passes")
    @Test
    void asGroupDefinitionException_Pass_GroupDefinitionException() {
        final RuntimeException actual = newGroupDefinitionException();
        final ValidationExceptionAssert a = new ValidationExceptionAssert(actual);
        final GroupDefinitionExceptionAssert as = a.asGroupDefinitionException();
    }
}
