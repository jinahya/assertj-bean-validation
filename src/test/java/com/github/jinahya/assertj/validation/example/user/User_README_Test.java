package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldFail;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldPass;

class User_README_Test {

    @Test
    void isValidFor__Name() {
        // @formatter:off
        shouldPass(() -> assertThatProperty("Jane").isValidFor(User.class, "name"));
        shouldFail(() -> assertThatProperty(  null).isValidFor(User.class, "name"));
        shouldFail(() -> assertThatProperty(    "").isValidFor(User.class, "name"));
        shouldFail(() -> assertThatProperty(   " ").isValidFor(User.class, "name"));
        // @formatter:on
    }

    @Test
    void isValidFor__Age() {
        // @formatter:off
        shouldPass(() -> assertThatProperty(  0).isValidFor(User.class, "age"));
        shouldPass(() -> assertThatProperty( 28).isValidFor(User.class, "age"));
        shouldFail(() -> assertThatProperty( -1).isValidFor(User.class, "age"));
        shouldFail(() -> assertThatProperty(300).isValidFor(User.class, "age"));
        // @formatter:on
    }
}
