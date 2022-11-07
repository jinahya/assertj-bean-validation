package com.github.jinahya.assertj.validation.example.user;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertConstraintViolation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConstraintViolationAssert_HasInvalidValue_Test {

    @Test
    void __NameIsInvalid() {
        final var user = User.newInstance(false, true);
        assertThatThrownBy(
                () -> assertBean(user)
                        .isValid(i -> {
                            assertThat(i).singleElement().satisfies(cv -> {
                                assertConstraintViolation(cv).hasInvalidValue(user.getName());
                            });
                        })
        )
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __AgeIsInvalid() {
        final var user = User.newInstance(true, false);
        assertThatThrownBy(() -> assertBean(user)
                .isValid(i -> {
                }))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __BothAreInvalid() {
        final var user = User.newInstance(false, false);
        assertThatThrownBy(
                () -> assertBean(user)
                        .isValid(i -> {
                        })
        )
                .isInstanceOf(AssertionError.class);
    }
}
