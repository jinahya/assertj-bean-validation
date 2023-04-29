package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;

class User_UsingValidatorSuppliedBy_Test {

    @DisplayName("usingValidator(null)")
    @Test
    void __Null() {
        final var user = User.newInstance(true, true);
        final var assertion = assertThatBean(user);
        assertion.usingValidatorSuppliedBy(null).isValid();
    }

    @DisplayName("usingValidator(non-null)")
    @Test
    void __NonNull() {
        final var user = User.newInstance(true, true);
        final var assertion = assertThatBean(user);
        assertion.usingValidatorSuppliedBy(() -> {
                    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                        return factory.getValidator();
                    }
                })
                .isValid();
    }
}
