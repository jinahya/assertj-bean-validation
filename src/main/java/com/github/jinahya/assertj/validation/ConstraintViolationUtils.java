package com.github.jinahya.assertj.validation;

import java.util.function.Function;

final class ConstraintViolationUtils {

    private static <R> R applyConstraintViolationClassFor(final Object instance,
                                                          final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor("ConstraintViolation", instance, function);
    }

    private static Class<?> getConstraintViolationClass(final Object instance) {
        return applyConstraintViolationClassFor(instance, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of either {@code javax.validation.ConstraintViolation} or
     * {@code jakarta.validation.ConstraintViolation}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code ConstraintViolation}; {@code false} otherwise.
     */
    static boolean isConstraintViolationInstance(final Object actual) {
        return applyConstraintViolationClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of either {@code javax.validation.ConstraintViolation} or {@code
     * jakarta.validation.ConstraintViolation}.
     *
     * @param actual the object to be tested.
     */
    static <T> T requireConstraintViolationInstance(final T actual) {
        return applyConstraintViolationClassFor(actual, c -> actual);
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue
    static Object getInvalidValue(final Object actual) {
        final String name = "getInvalidValue";
        try {
            return getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean
    static Object getLeafBean(final Object actual) {
        final String name = "getLeafBean";
        try {
            return getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------------ getMessage
    static String getMessage(final Object actual) {
        final String name = "getMessage";
        try {
            return (String) getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath
    @SuppressWarnings({"unchecked"})
    static <PathType extends Iterable<?>> PathType getPropertyPath(final Object actual) {
        final String name = "getPropertyPath";
        try {
            return (PathType) getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    static Object getRootBean(final Object actual) {
        final String name = "getRootBean";
        try {
            return getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    static Class<?> getRootBeanClass(final Object actual) {
        final String name = "getRootBeanClass";
        try {
            return (Class<?>) getConstraintViolationClass(actual).getMethod(name).invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
