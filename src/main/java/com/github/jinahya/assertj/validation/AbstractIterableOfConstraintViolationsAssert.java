package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>, ...
})
public abstract class AbstractIterableOfConstraintViolationsAssert<
        // @formatter:off
            SELF extends AbstractIterableOfConstraintViolationsAssert<SELF, T>,
            T
        // @formatter:on
        >
        extends AbstractIterableAssert<
        // @formatter:off
            SELF,
            Iterable<? extends ConstraintViolation<T>>,
            ConstraintViolation<T>,
        ConstraintViolationAssert<T>
        // @formatter:on
        > {

    protected AbstractIterableOfConstraintViolationsAssert(final Iterable<? extends ConstraintViolation<T>> actual,
                                                           final Class<?> selfType) {
        super(actual, selfType);
    }
}
