package com.github.jinahya.assertj.validation.user;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class UserAssert_Conditions_Test
        extends UserAssertTest {

    @Test
    void isNamedJane__() {
        final User actual = User.newValidInstance_().name("Jane").build();
        assertInstant(actual)
                .isNamedJane();
    }

    @Test
    void isNamedJane_Fail_John() {
        final User actual = User.newValidInstance_().name("John").build();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(assertion::isNamedJane)
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void isNamedJohn__() {
        final User actual = User.newValidInstance_().name("John").build();
        assertInstant(actual)
                .isNamedJohn();
    }

    @Test
    void isNamedJohn_Fail_Jane() {
        final User actual = User.newValidInstance_().name("Jane").build();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(assertion::isNamedJohn)
                .isInstanceOf(AssertionError.class);
    }
}
