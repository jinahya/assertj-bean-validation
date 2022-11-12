package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;

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
        assertThatCode(() -> assertion.isValidFor(User.class, "age", i -> {
            assertThat(i).isEmpty();
        }))
                .doesNotThrowAnyException();
    }

    @Test
    void __invalidAge() {
        final var age = User.invalidAge();
        final var assertion = assertThatProperty(age);
        assertThatThrownBy(
                () -> assertion.isValidFor(
                        User.class,
                        "age",
                        i -> {
                            assertThatIterableConstraintViolations(i)
                                    .<ConstraintViolationAssert<?, User>>singleElement(new InstanceOfAssertFactory<>(
                                            ConstraintViolation.class, ValidationAssertions::assertThatConstraintViolation))
                                    .hasRootBeanClass(User.class)
                                    .hasRootBean(age)
                                    .hasInvalidValue(age)
                            ;
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
