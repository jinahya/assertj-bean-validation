package com.github.jinahya.assertj.validation;

import org.assertj.core.api.InstanceOfAssertFactory;

import javax.validation.ConstraintViolation;

public class ValidationInstanceOfAssertFactories {

    public static <T, U extends ConstraintViolation<T>> InstanceOfAssertFactory<U, AbstractConstraintViolationAssert<?, U>>
    constraintViolation() {
//        return new InstanceOfAssertFactory<>(ConstraintViolation.class, cv -> ValidationAssertions.assertThat((U) cv));
        return null;
    }

    protected ValidationInstanceOfAssertFactories() {
        super();
    }
}
