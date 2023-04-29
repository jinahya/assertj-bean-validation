package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;

class User_UsingValidator_Test {

    @DisplayName("usingValidator(null)")
    @Test
    void __Null() {
        final var user = User.newInstance(true, true);
        final var assertion = assertThatBean(user);
        assertion.usingValidator(null).isValid();
    }

    @DisplayName("usingValidator(non-null)")
    @Test
    void __NonNull() {
        final var user = User.newInstance(true, true);
        final var assertion = assertThatBean(user);
        final Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        assertion.usingValidator(validator).isValid();
    }

    @DisplayName("usingValidator(non-null)")
    @Test
    void ___NonNull() {
        final var user = User.newInstance(true, true);
        final var assertion = assertThatBean(user);
        final Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        assertion.usingValidator(validator).isValid();
    }
}
