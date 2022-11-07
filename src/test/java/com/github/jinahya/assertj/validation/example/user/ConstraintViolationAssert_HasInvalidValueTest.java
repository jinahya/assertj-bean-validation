package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConstraintViolationAssert_HasInvalidValueTest {

    @Test
    void __NameIsInvalid() {
        final var user = User.newInstance(false, true);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .isValid(i -> {
                        })
        )
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __AgeIsInvalid() {
        final var user = User.newInstance(true, false);
        assertThatThrownBy(() -> assertThatBean(user)
                .isValid(i -> {
                }))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __BothAreInvalid() {
        final var user = User.newInstance(false, false);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .isValid(i -> {
                        })
        )
                .isInstanceOf(AssertionError.class);
    }
}
