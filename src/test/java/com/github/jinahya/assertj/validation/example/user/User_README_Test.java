package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldFail;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldPass;
import static com.github.jinahya.assertj.validation.example.user.User.invalidAge;
import static com.github.jinahya.assertj.validation.example.user.User.invalidName;
import static com.github.jinahya.assertj.validation.example.user.User.userWith;
import static com.github.jinahya.assertj.validation.example.user.User.validAge;
import static com.github.jinahya.assertj.validation.example.user.User.validAgeForJunior;
import static com.github.jinahya.assertj.validation.example.user.User.validAgeForSenior;
import static com.github.jinahya.assertj.validation.example.user.User.validName;

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

    @Test
    void isValidFor__User() {
        shouldPass(() -> {
            assertThatProperty(userWith(validName(), validAge()))
                    .isValidFor(Registration.class, "user");
        });
        shouldPass(() -> {
            assertThatProperty(userWith(validName(), validAgeForJunior()))
                    .isValidFor(JuniorRegistration.class, "user");
        });
        shouldPass(() -> {
            assertThatProperty(userWith(validName(), validAgeForSenior()))
                    .isValidFor(SeniorRegistration.class, "user");
        });
    }

    @Test
    void isValid__() {
        // @formatter:off
        shouldPass(() -> assertThatBean(userWith(  validName(),   validAge())).isValid());
        shouldFail(() -> assertThatBean(userWith(invalidName(),   validAge())).isValid());
        shouldFail(() -> assertThatBean(userWith(  validName(), invalidAge())).isValid());
        shouldFail(() -> assertThatBean(userWith(invalidName(), invalidAge())).isValid());
        // @formatter:on
    }

    @Test
    void isNotValid__() {
        // @formatter:off
        shouldFail(() -> assertThatBean(userWith(  validName(),   validAge())).isNotValid());
        shouldPass(() -> assertThatBean(userWith(invalidName(),   validAge())).isNotValid());
        shouldPass(() -> assertThatBean(userWith(  validName(), invalidAge())).isNotValid());
        shouldPass(() -> assertThatBean(userWith(invalidName(), invalidAge())).isNotValid());
        // @formatter:on
    }
}
