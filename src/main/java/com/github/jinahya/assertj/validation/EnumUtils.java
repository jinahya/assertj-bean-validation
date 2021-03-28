package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

final class EnumUtils {

    static String name(final Class<?> enumClass, final Object enumConstant) {
        requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not enum: " + enumClass);
        }
        try {
            return (String) enumClass.getMethod("name").invoke(enumConstant);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke name() on " + enumConstant, roe);
        }
    }

    @SuppressWarnings("unchecked")
    static Object valueOf(final Class<?> enumClass, final String name) {
        requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not enum: " + enumClass);
        }
        requireNonNull(name, "name is null");
        return Enum.valueOf((Class<? extends Enum>) enumClass, name);
    }

    private EnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
