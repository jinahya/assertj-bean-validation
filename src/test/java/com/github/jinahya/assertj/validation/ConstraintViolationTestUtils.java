package com.github.jinahya.assertj.validation;

public class ConstraintViolationTestUtils {

    public static Object getInvalidValue(final Object violation) {
        return ConstraintViolationUtils.getInvalidValue(violation);
    }

    public static Object getLeafBean(final Object violation) {
        return ConstraintViolationUtils.getLeafBean(violation);
    }

    public static String getMessage(final Object violation) {
        return ConstraintViolationUtils.getMessage(violation);
    }

    public static Iterable<?> getPropertyPath(final Object actual) {
        return ConstraintViolationUtils.getPropertyPath(actual);
    }

    public static Object getRootBean(final Object violation) {
        return ConstraintViolationUtils.getRootBean(violation);
    }

    public static Class<?> getRootBeanClass(final Object violation) {
        return ConstraintViolationUtils.getRootBeanClass(violation);
    }

    private ConstraintViolationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
