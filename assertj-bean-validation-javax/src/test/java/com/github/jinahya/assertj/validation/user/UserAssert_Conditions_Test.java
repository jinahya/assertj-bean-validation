package com.github.jinahya.assertj.validation.user;

import lombok.extern.slf4j.Slf4j;
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
    void isNamedJohn__() {
        final User actual = User.newValidInstance_().name("John").build();
        assertInstant(actual)
                .isNamedJohn();
    }
}
