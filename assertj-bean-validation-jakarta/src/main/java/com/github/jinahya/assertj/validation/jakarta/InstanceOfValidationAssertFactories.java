package com.github.jinahya.assertj.validation.jakarta;

import jakarta.validation.ConstraintViolation;
import org.assertj.core.api.InstanceOfAssertFactory;

public interface InstanceOfValidationAssertFactories {

    @SuppressWarnings({"unchecked"})
    InstanceOfAssertFactory<ConstraintViolation<?>, ConstraintViolationAssert<?>> CONSTRAINT_VIOLATION =
            new InstanceOfAssertFactory<>((Class<ConstraintViolation<?>>) (Object) ConstraintViolation.class,
                                          ConstraintViolationAssert::new);
}
