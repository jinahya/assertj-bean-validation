package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;

import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionUtils.GROUP_DEFINITION_EXCEPTION_CLASS;

final class GroupDefinitionExceptionTestUtils {

    static RuntimeException newGroupDefinitionException() {
        try {
            final Constructor<?> constructor = GROUP_DEFINITION_EXCEPTION_CLASS.getConstructor();
            final Object instance = constructor.newInstance();
            return (RuntimeException) instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private GroupDefinitionExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
