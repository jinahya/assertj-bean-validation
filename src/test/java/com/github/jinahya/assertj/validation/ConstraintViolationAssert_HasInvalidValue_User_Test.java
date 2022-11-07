package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConstraintViolationAssert_HasInvalidValue_User_Test {

    @Test
    void __NameIsInvalid() {
        final var user = User.newInstance(false, true);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .consumingViolations(cv -> {
                            assertThat(cv).hasInvalidValue(user.getName());
                        })
                        .isValid()
        )
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __AgeIsInvalid() {
        final var user = User.newInstance(true, false);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .consumingViolations(cv -> {
                            assertThat(cv).hasInvalidValue(user.getAge());
                        })
                        .isValid()
        )
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __BothAreInvalid() {
        final var user = User.newInstance(false, false);
        final Set<Object> invalidValues = new HashSet<>();
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .consumingViolations(cv -> {
                            invalidValues.add(cv.getInvalidValue());
                        })
                        .isValid()
        )
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(invalidValues)
                .contains(user.getName(), user.getAge());
    }
}
