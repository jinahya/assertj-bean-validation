package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldFail;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldPass;

class User_README_Test {

    @Test
    void isValidFor__() {
        // @formatter:off
        shouldPass(() -> assertThatProperty("Jane").isValidFor(User.class, "name")); // should pass
        shouldFail(() -> assertThatProperty(  null).isValidFor(User.class, "name")); // should fail
        shouldFail(() -> assertThatProperty(    "").isValidFor(User.class, "name")); // should fail
        shouldFail(() -> assertThatProperty(   " ").isValidFor(User.class, "name")); // should fail
        shouldPass(() -> assertThatProperty(     0).isValidFor(User.class,  "age")); // should pass
        shouldPass(() -> assertThatProperty(    28).isValidFor(User.class,  "age")); // should pass
        shouldFail(() -> assertThatProperty(    -1).isValidFor(User.class,  "age")); // should fail
        shouldFail(() -> assertThatProperty(   300).isValidFor(User.class,  "age")); // should fail
        // @formatter:on
    }
}
