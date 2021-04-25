package com.github.jinahya.assertj.validation.jakarta;

import org.assertj.core.api.InstanceOfAssertFactory;

import javax.validation.ConstraintViolation;

public interface InstanceOfValidationAssertFactories {

    @SuppressWarnings({"unchecked"})
    InstanceOfAssertFactory<ConstraintViolation<?>, ConstraintViolationAssert<?>> CONSTRAINT_VIOLATION =
            new InstanceOfAssertFactory<>((Class<ConstraintViolation<?>>) (Object) ConstraintViolation.class,
                                          ConstraintViolationAssert::new);
}
