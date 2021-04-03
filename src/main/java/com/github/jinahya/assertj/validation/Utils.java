package com.github.jinahya.assertj.validation;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class Utils {

    private static Class<?> classJavax(final String name) throws ClassNotFoundException {
        return Class.forName("javax.validation." + name);
    }

    private static Class<?> classJakarta(final String name) throws ClassNotFoundException {
        return Class.forName("jakarta.validation." + name);
    }

    static <R> R applyClass(final String suffix, final Function<? super Class<?>, ? extends R> function) {
        requireNonNull(suffix, "suffix is null");
        requireNonNull(function, "function is null");
        try {
            return function.apply(classJavax(suffix));
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        try {
            return function.apply(classJakarta(suffix));
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        throw new RuntimeException("unable to get class for " + suffix);
    }

    static <R> R applyClassFor(final String suffix, final Object instance,
                               final Function<? super Class<?>, ? extends R> function) {
        requireNonNull(suffix, "suffix is null");
        requireNonNull(instance, "instance is null");
        requireNonNull(function, "function is null");
        try {
            final Class<?> c = classJavax(suffix);
            if (c.isInstance(instance)) {
                return function.apply(c);
            }
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        try {
            final Class<?> c = classJakarta(suffix);
            if (c.isInstance(instance)) {
                return function.apply(c);
            }
        } catch (final ClassNotFoundException cnfe) {
            // empty;
        }
        throw new RuntimeException("unable to get class for " + suffix + " with " + instance);
    }

    static Class<?> getClassFor(final String suffix, final Object instance) {
        return applyClassFor(suffix, instance, Function.identity());
    }

    static <R> R applyClass(final Supplier<? extends Class<?>> classSupplier,
                            final Function<? super Class<?>, ? extends R> classFunction) {
        requireNonNull(classSupplier, "classSupplier is null");
        requireNonNull(classFunction, "methodFunction is null");
        return classFunction.apply(classSupplier.get());
    }

    static <R> R applyClass(final String suffix, final Object instance,
                            final Function<? super Class<?>, ? extends R> classFunction) {
        requireNonNull(classFunction, "classFunction is null");
        return applyClassFor(suffix, instance, c -> applyClass(() -> c, classFunction));
    }

    static Method getMethod(final Supplier<? extends Class<?>> classSupplier,
                            final Function<? super Class<?>, ? extends Method> methodFunction) {
        return applyClass(classSupplier, methodFunction);
    }

    static Method getMethod(final String suffix, final Object actual,
                            final Function<? super Class<?>, ? extends Method> methodFunction) {
        return applyClass(suffix, actual, c -> getMethod(() -> c, methodFunction));
    }

    private Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}