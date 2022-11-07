package com.github.jinahya.assertj.validation;

import org.mockito.ArgumentCaptor;

import javax.validation.ConstraintViolation;
import java.util.Objects;
import java.util.function.Consumer;

import static org.mockito.Mockito.spy;

public final class ValidationAssertionsTestUtils {

    public static <T> Consumer<Iterable<ConstraintViolation<T>>> violationsConsumerSpy(
            final Consumer<? super Iterable<? extends ConstraintViolation<T>>> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        final Consumer<Iterable<ConstraintViolation<T>>> wrapper = new Consumer<>() { // DO NOT REPLACE WITH LAMBDA!!!
            @Override
            public void accept(final Iterable<ConstraintViolation<T>> violations) {
                consumer.accept(violations);
            }
        };
        return spy(wrapper);
    }

    public static <T> Consumer<Iterable<ConstraintViolation<T>>> violationsConsumerSpy() {
        return violationsConsumerSpy(cv -> {
        });
    }

    public static <T> ArgumentCaptor<Iterable<ConstraintViolation<T>>> constraintViolationsCaptor() {
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<Iterable<ConstraintViolation<T>>> captor = ArgumentCaptor.forClass(Iterable.class);
        return captor;
    }

    private ValidationAssertionsTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
