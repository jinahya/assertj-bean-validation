package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;
import java.util.Set;

import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionUtils.CONSTRAINT_VIOLATION_EXCEPTION_CLASS;
import static java.util.Collections.emptySet;

final class ConstraintViolationExceptionTestUtils {

    static RuntimeException newConstraintViolationException(final Set<Object> constraintViolations) {
        try {
            final Constructor<?> constructor = CONSTRAINT_VIOLATION_EXCEPTION_CLASS.getConstructor(Set.class);
            final Object instance = constructor.newInstance(constraintViolations);
            return (RuntimeException) instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    static RuntimeException newConstraintViolationException() {
        return newConstraintViolationException(emptySet());
    }

    private ConstraintViolationExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
