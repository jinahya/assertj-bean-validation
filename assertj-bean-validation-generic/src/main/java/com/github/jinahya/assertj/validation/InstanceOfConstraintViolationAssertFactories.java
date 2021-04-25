package com.github.jinahya.assertj.validation;

import org.assertj.core.api.InstanceOfAssertFactory;

public interface InstanceOfConstraintViolationAssertFactories {

    static <T> InstanceOfAssertFactory<Object, ConstraintViolationAssert<T>> constraintViolation() {
        return new InstanceOfAssertFactory<>(Object.class, ConstraintViolationAssert::new);
    }
}
