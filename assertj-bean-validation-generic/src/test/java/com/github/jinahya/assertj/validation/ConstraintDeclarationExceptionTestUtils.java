package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;

import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionUtils.CONSTRAINT_DECLARATION_EXCEPTION_CLASS;

final class ConstraintDeclarationExceptionTestUtils {

    static RuntimeException newConstraintDeclarationException() {
        try {
            final Constructor<?> constructor = CONSTRAINT_DECLARATION_EXCEPTION_CLASS.getConstructor();
            final Object instance = constructor.newInstance();
            return (RuntimeException) instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private ConstraintDeclarationExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
