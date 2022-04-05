package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.AbstractBeanAssertTest;

abstract class UserAssertTest
        extends AbstractBeanAssertTest<UserAssert, User> {

    protected UserAssertTest() {
        super(UserAssert.class, User.class);
    }
}
