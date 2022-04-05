package com.github.jinahya.assertj.validation;

import javax.validation.Validator;
import java.lang.reflect.Constructor;

public abstract class AbstractBeanAssertTest<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssertTest<SELF, ACTUAL, Validator> {

    protected AbstractBeanAssertTest(final Class<SELF> assertionClass, final Class<ACTUAL> actualClass) {
        super(assertionClass, actualClass, Validator.class);
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