package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>, ...
})
public abstract class IterableConstraintViolationAssert<
        // @formatter:off
            SELF extends IterableConstraintViolationAssert<SELF, T>,
            T
        // @formatter:on
        >
        extends AbstractIterableAssert<
        // @formatter:off
            SELF,
            Iterable<? extends ConstraintViolation<T>>,
            ConstraintViolation<T>,
            ConstraintViolationAssertImpl<T>
        // @formatter:on
        > {

    protected IterableConstraintViolationAssert(final Iterable<? extends ConstraintViolation<T>> actual,
                                                final Class<?> selfType) {
        super(actual, selfType);
    }
}
