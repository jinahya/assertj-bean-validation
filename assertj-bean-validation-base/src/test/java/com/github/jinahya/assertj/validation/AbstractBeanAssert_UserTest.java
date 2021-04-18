package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.user.User;

public abstract class AbstractBeanAssert_UserTest<
        SELF extends AbstractBeanAssert<SELF, User, VALIDATOR, CONSTRAINT_VIOLATION>,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractBeanAssertTest<SELF, User, VALIDATOR, CONSTRAINT_VIOLATION> {

    protected AbstractBeanAssert_UserTest(final Class<SELF> assertClass) {
        super(assertClass);
    }
}