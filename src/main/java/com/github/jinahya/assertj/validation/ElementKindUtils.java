package com.github.jinahya.assertj.validation;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class ElementKindUtils {

    private static <R> R applyElementKindClassFor(final Object instance,
                                                  final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor("ElementKind", instance, function);
    }

    private static Class<?> getElementKindClass(final Object instance) {
        return applyElementKindClassFor(instance, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of either {@code javax.validation.ElementKind} or {@link
     * jakarta.validation.ElementKind}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code ElementKind}; {@code false} otherwise.
     */
    static boolean isElementKindInstance(final Object actual) {
        return applyElementKindClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of either {@code javax.validation.ElementKind} or {@link
     * jakarta.validation.ElementKind}.
     *
     * @param actual the object to be tested.
     */
    static <T> T requireElementKindInstance(final T actual) {
        return applyElementKindClassFor(actual, c -> actual);
    }

    static String name(final Object actual) {
        requireNonNull(actual, "actual is null");
        if (!actual.getClass().isEnum()) {
            throw new IllegalArgumentException("not an enum constant: " + actual);
        }
        return EnumUtils.name(actual.getClass(), actual);
    }

    private ElementKindUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
