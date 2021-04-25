package com.github.jinahya.assertj.validation.jakarta;

import jakarta.validation.ConstraintViolation;
import org.assertj.core.api.InstanceOfAssertFactory;

public interface InstanceOfConstraintViolationAssertFactories {

    @SuppressWarnings({"unchecked"})
    static <T> InstanceOfAssertFactory<ConstraintViolation<T>, ConstraintViolationAssert<T>> constraintViolation() {
        return new InstanceOfAssertFactory<>((Class<ConstraintViolation<T>>) (Object) ConstraintViolation.class,
                                             ConstraintViolationAssert::new);
    }
}
