package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

final class BeanValidationTestUtils {

    static Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    static <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
        return validator().validate(object, groups);
    }

    static <T> boolean isValid(final T object, final Class<?>... groups) {
        return validate(object, groups).isEmpty();
    }

    static <T> T requireValid(final T object, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> constraintViolations = validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return object;
    }

    BeanValidationTestUtils() {
        super();
    }
}
