package com.github.jinahya.assertj.validation;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public final class ParameterizedTestUtils {

    public static <B, A extends AbstractBeanAssert<A, ?, ?, ?>> Stream<A> beanAssertions(
            final Stream<? extends B> beans,
            final Function<? super B, ? extends A> mapper) {
        requireNonNull(beans, "beans is null");
        requireNonNull(mapper, "mapper is null");
        return beans.map(mapper);
    }

    public static <V, A extends AbstractValueAssert<A, ?, ?>> Stream<A> valueAssertions(
            final Stream<? extends V> values,
            final Function<? super V, ? extends A> mapper) {
        requireNonNull(values, "values is null");
        requireNonNull(mapper, "mapper is null");
        return values.map(mapper);
    }

    private ParameterizedTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
