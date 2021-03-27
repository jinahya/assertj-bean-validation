package com.github.jinahya.assertj.validation;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class Utils {

    private static Class<?> classJavax(final String name) throws ClassNotFoundException {
        return Class.forName("javax.validation." + name);
    }

    private static Class<?> classJakarta(final String name) throws ClassNotFoundException {
        return Class.forName("jakarta.validation." + name);
    }

    static <R> R classFor(final String name, final Object instance,
                          final Function<? super Class<?>, ? extends R> function) {
        requireNonNull(name, "name is null");
        requireNonNull(instance, "instance is null");
        requireNonNull(function, "function is null");
        try {
            final Class<?> c = classJavax(name);
            if (c.isInstance(instance)) {
                return function.apply(c);
            }
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        try {
            final Class<?> c = classJakarta(name);
            if (c.isInstance(instance)) {
                return function.apply(c);
            }
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        throw new RuntimeException("no class found for " + name + " and " + instance);
    }

    private Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}