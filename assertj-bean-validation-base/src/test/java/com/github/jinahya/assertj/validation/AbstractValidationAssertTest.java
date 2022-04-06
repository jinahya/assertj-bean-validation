package com.github.jinahya.assertj.validation;

import java.lang.reflect.Constructor;

abstract class AbstractValidationAssertTest<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends ValidationAssertTest<SELF, ACTUAL, VALIDATOR> {

    protected AbstractValidationAssertTest(final Class<SELF> assertClass, final Class<ACTUAL> actualClass,
                                           final Class<VALIDATOR> validatorClass) {
        super(assertClass, actualClass, validatorClass);
    }

    protected SELF assertInstant(final ACTUAL actual) {
        try {
            final Constructor<SELF> constructor = assertClass.getDeclaredConstructor(actualClass);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }
}