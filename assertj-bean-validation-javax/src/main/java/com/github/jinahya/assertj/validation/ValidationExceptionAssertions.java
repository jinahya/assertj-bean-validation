package com.github.jinahya.assertj.validation;

import javax.validation.ValidationException;

import static java.util.Objects.requireNonNull;

public final class ValidationExceptionAssertions {

    public static ValidationExceptionAssert assertValidationException(final ValidationException actual) {
        return new ValidationExceptionAssert(actual);
    }

    public static ValidationExceptionAssert assertThat(final AbstractWrapper<? extends ValidationException> wrapper) {
        return assertValidationException(requireNonNull(wrapper).getActual());
    }

    private ValidationExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
