package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>, ...
})
public class IterableConstraintViolationAssert<
        SELF extends IterableConstraintViolationAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
        ACTUAL extends Iterable<? extends ELEMENT>,
        ELEMENT extends ConstraintViolation<?>,
        ELEMENT_ASSERT extends ConstraintViolationAssert<ELEMENT_ASSERT, ELEMENT>
        >
        extends AbstractIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT> {

    protected IterableConstraintViolationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    protected ELEMENT_ASSERT toAssert(final ELEMENT element, final String description) {
        return null;
    }

    @Override
    protected SELF newAbstractIterableAssert(final Iterable<? extends ELEMENT> iterable) {
        return null;
    }
}
