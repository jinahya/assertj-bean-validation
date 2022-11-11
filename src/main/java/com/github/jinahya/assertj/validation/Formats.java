package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Formats {

    static String format(final ConstraintViolation<?> violation) {
        Objects.requireNonNull(violation, "violation is null");
        return String.format(
                "message        : %1$s%n" +
                "\t   propertyPath   : %2$s%n" +
                "\t   rootBeanClass  : %3$s%n" +
                "\t   messageTemplate: %4$s",
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
                .mapToObj(i -> String.format("\t-> %1$s", format(iterator.next())))
                .collect(Collectors.joining("\t%n"));
    }

    private Formats() {
        throw new AssertionError("instantiation is not allowed");
    }
}
