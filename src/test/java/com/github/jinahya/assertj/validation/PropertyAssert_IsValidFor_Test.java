package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PropertyAssert_IsValidFor_Test {

    @Nested
    class User_Age_Test {

        @Test
        void __validAge() {
            final var age = User.validAge();
            final var assertion = assertThatProperty(age);
            assertThatCode(() -> assertion.isValidFor(User.class, "age"))
                    .doesNotThrowAnyException();
        }

        @Test
        void __invalidAge() {
            final var age = User.invalidAge();
            final var assertion = assertThatProperty(age);
            assertThatThrownBy(() -> assertion.isValidFor(User.class, "age"))
                    .isInstanceOf(AssertionError.class)
                    .satisfies(ae -> {
                        log.debug("message: {}", ae.getMessage());
                    });
        }
    }

    @Nested
    class User_Name_Test {

        @Test
        void __validName() {
            final var name = User.validName();
            final var assertion = assertThatProperty(name);
            assertThatThrownBy(() -> assertion.isNotValidFor(User.class, "name"))
                    .isInstanceOf(AssertionError.class)
                    .satisfies(ar -> {
                        log.debug("message: {}", ar.getMessage());
                    });
        }

        @Test
        void __invalidName() {
            final var name = User.invalidName();
            final var assertion = assertThatProperty(name);
            assertThatCode(() -> assertion.isNotValidFor(User.class, "name"))
                    .doesNotThrowAnyException();
        }
    }
}
