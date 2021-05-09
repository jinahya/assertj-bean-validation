package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;

import static com.github.jinahya.assertj.validation.ValidationExceptionUtils.VALIDATION_EXCEPTION_CLASS;

final class ValidationExceptionTestUtils {

    static RuntimeException newValidationException() {
        try {
            final Constructor<?> constructor = VALIDATION_EXCEPTION_CLASS.getConstructor();
            final Object instance = constructor.newInstance();
            return (RuntimeException) instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private ValidationExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
