package com.github.jinahya.assertj.validation;

import org.assertj.core.api.InstanceOfAssertFactory;

public interface InstanceOfValidationAssertFactories {

    InstanceOfAssertFactory<Object, ConstraintViolationAssert<?>> CONSTRAINT_VIOLATION =
            new InstanceOfAssertFactory<>(Object.class, ConstraintViolationAssert::new);
}
