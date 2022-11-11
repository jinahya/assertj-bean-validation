package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

public class IterableConstraintViolationAssert<
        SELF extends IterableConstraintViolationAssert<SELF, ACTUAL, T>,
        ACTUAL extends Iterable<? extends T>,
        T extends ConstraintViolation<?>>
        extends AbstractIterableAssert<SELF, ACTUAL, ConstraintViolation<?>, AbstractConstraintViolationAssert<?, T>> {

    protected IterableConstraintViolationAssert(final ACTUAL constraintViolations, final Class<?> selfType) {
        super(constraintViolations, selfType);
    }

    @Override
    protected AbstractConstraintViolationAssert<?, T> toAssert(ConstraintViolation<?> constraintViolation, String s) {
        return null;
    }

    @Override
    protected SELF newAbstractIterableAssert(Iterable<? extends ConstraintViolation<?>> iterable) {
        return null;
    }
}
