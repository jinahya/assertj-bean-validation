package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import java.util.function.Consumer;

import static org.mockito.Mockito.spy;

public final class ValidationAssertionsTestUtils {

    public static Consumer<ConstraintViolation<?>> violationConsumerSpy(
            final Consumer<? super ConstraintViolation<?>> consumer) {
        final Consumer<ConstraintViolation<?>> wrapper = new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
                consumer.accept(constraintViolation);
            }
        };
        return spy(wrapper);
    }

    public static Consumer<ConstraintViolation<?>> violationConsumerSpy() {
        return violationConsumerSpy(cv -> {
        });
    }

    private ValidationAssertionsTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
