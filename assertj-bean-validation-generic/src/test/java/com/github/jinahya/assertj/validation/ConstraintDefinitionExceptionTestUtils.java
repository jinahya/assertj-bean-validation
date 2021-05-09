package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;

import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionUtils.CONSTRAINT_DEFINITION_EXCEPTION_CLASS;

final class ConstraintDefinitionExceptionTestUtils {

    static RuntimeException newConstraintDefinitionException() {
        try {
            final Constructor<?> constructor = CONSTRAINT_DEFINITION_EXCEPTION_CLASS.getConstructor();
            final Object instance = constructor.newInstance();
            return (RuntimeException) instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private ConstraintDefinitionExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
