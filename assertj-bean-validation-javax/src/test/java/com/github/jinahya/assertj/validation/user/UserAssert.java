package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.AbstractBeanAssert;
import org.assertj.core.api.Assertions;

class UserAssert
        extends AbstractBeanAssert<UserAssert, User> {

    UserAssert(final User actual) {
        super(actual, UserAssert.class);
    }

    public UserAssert isNamedJane() {
        return is(UserConditions.NAMED_JANE);
    }

    public UserAssert isNamedJohn() {
        return is(UserConditions.NAMED_JOHN);
    }

    public UserAssert hasAge(final int expected) {
        return isNotNull()
                .satisfies(a -> Assertions.assertThat(a.getAge()).isEqualTo(expected));
    }
}
