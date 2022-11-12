package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatIterableConstraintViolations;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PropertyAssert_IsValidFor_User_Test {

    @Test
    void __validAge() {
        final var age = User.validAge();
        final var assertion = assertThatProperty(age);
        assertThatCode(
                () -> assertion.isValidFor(
                        User.class,
                        "age",
                        i -> {
                            assertThat(i).isEmpty();
                        })
        )
                .doesNotThrowAnyException();
    }

    @Test
    void __invalidAge() {
        final var age = User.invalidAge();
        final var assertion = assertThatProperty(age);
        assertThatThrownBy(
                () -> assertion.isValidFor(
                        User.class,
                        "age"
                        ,
                        i -> {
                            assertThatIterableConstraintViolations(i)
                                    .singleElement()
                                    .hasRootBeanClass(User.class)
                                    .hasRootBean(null)
                                    .hasInvalidValue(age);
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @Test
    void __validName() {
        final var name = User.validName();
        final var assertion = assertThatProperty(name);
        assertThatCode(
                () -> assertion.isValidFor(
                        User.class,
                        "name",
                        i -> {
                            assertThat(i).isEmpty();
                        })
        )
                .doesNotThrowAnyException();
    }

    @Test
    void __invalidName() {
        final var name = User.invalidName();
        final var assertion = assertThatProperty(name);
        assertThatThrownBy(
                () -> assertion.isValidFor(
                        User.class,
                        "name",
                        i -> {
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae);
                });
    }
}
