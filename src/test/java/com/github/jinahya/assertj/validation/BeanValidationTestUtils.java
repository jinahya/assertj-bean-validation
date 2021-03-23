package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class BeanValidationTestUtils {

    public static Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    static <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
        return validator().validate(object, groups);
    }

    public static <T> boolean isValid(final T object, final Class<?>... groups) {
        return validate(object, groups).isEmpty();
    }

    public static <T> T requireValid(final T object, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> constraintViolations = validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return object;
    }

    BeanValidationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
