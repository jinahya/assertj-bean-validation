package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>, ...
})
class IterableConstraintViolationAssertImpl<T>
        extends IterableConstraintViolationAssert<IterableConstraintViolationAssertImpl<T>, T> {

    IterableConstraintViolationAssertImpl(final Iterable<? extends ConstraintViolation<T>> actual) {
        super(actual, ConstraintViolationAssertImpl.class);
    }

    @Override
    protected ConstraintViolationAssertImpl<T> toAssert(final ConstraintViolation<T> value, final String description) {
        return new ConstraintViolationAssertImpl<>(value)
                .describedAs(description);
    }

    @Override
    protected IterableConstraintViolationAssertImpl<T> newAbstractIterableAssert(
            final Iterable<? extends ConstraintViolation<T>> iterable) {
        return new IterableConstraintViolationAssertImpl<>(iterable);
    }
}
