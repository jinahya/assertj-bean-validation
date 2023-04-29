package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;

class IterableOfConstraintViolationsAssert<T>
        extends AbstractIterableOfConstraintViolationsAssert<IterableOfConstraintViolationsAssert<T>, T> {

    IterableOfConstraintViolationsAssert(final Iterable<? extends ConstraintViolation<T>> actual) {
        super(actual, IterableOfConstraintViolationsAssert.class);
    }

    @Override
    protected ConstraintViolationAssert<T> toAssert(final ConstraintViolation<T> value, final String description) {
        return new ConstraintViolationAssert<>(value)
                .describedAs(description);
    }

    @Override
    protected IterableOfConstraintViolationsAssert<T> newAbstractIterableAssert(
            final Iterable<? extends ConstraintViolation<T>> iterable) {
        return new IterableOfConstraintViolationsAssert<>(iterable);
    }
}
