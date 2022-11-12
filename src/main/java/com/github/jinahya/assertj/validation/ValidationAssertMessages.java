package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class ValidationAssertMessages {

    static String format(final ConstraintViolation<?> violation) {
        Objects.requireNonNull(violation, "violation is null");
        return String.format(
                "message        : %1$s%n" +
                "   propertyPath   : %2$s%n" +
                "   rootBeanClass  : %3$s%n" +
                "   messageTemplate: %4$s",
                violation.getMessage(),
                violation.getPropertyPath(),
                violation.getRootBeanClass(),
                violation.getMessageTemplate()
        );
    }

    static String format(final Set<? extends ConstraintViolation<?>> violations) {
        if (Objects.requireNonNull(violations, "violations is null").isEmpty()) {
            throw new IllegalArgumentException("empty violations");
        }
        final Iterator<? extends ConstraintViolation<?>> iterator = violations.iterator();
        return IntStream.range(0, violations.size())
                .mapToObj(i -> String.format("-> %1$s", format(iterator.next())))
                .collect(Collectors.joining("%n"));
    }

    private ValidationAssertMessages() {
        throw new AssertionError("instantiation is not allowed");
    }
}
