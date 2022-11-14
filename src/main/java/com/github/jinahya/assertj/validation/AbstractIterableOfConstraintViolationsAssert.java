package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>
})
abstract class AbstractIterableOfConstraintViolationsAssert<
        SELF extends AbstractIterableOfConstraintViolationsAssert<SELF, T>, T>
        extends AbstractIterableAssert<
        SELF, Iterable<? extends ConstraintViolation<T>>, ConstraintViolation<T>, ConstraintViolationAssert<T>> {

    protected AbstractIterableOfConstraintViolationsAssert(final Iterable<? extends ConstraintViolation<T>> actual,
                                                           final Class<?> selfType) {
        super(actual, selfType);
    }
}
