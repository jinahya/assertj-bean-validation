package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.AbstractBeanAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Slf4j
class UserAssert_IsValid_Test
        extends UserAssertTest {

    @DisplayName("(Valid) should pass")
    @Test
    void isValid_Pass_Valid() {
        final User actual = User.newValidInstance();
        final UserAssert assertion = assertInstant(actual);
        assertion.isValid();
    }

    @DisplayName("(WithInvalidName) should fail")
    @Test
    void isValid_Fail_InvalidName() {
        final AbstractBeanAssert<?, User> assertion
                = ValidationAssertions.assertBean(User.newInstanceWithInvalidName());
        Assertions.assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("(WithInvalidName, Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidName() {
        final AbstractBeanAssert<?, User> assertion
                = ValidationAssertions.assertBean(User.newInstanceWithInvalidName());
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValid(cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty();
    }

    @DisplayName("(WithInvalidAge) should fail")
    @Test
    void isValid_Fail_InvalidAge() {
        final AbstractBeanAssert<?, User> assertion
                = ValidationAssertions.assertBean(User.newInstanceWithInvalidAge());
        Assertions.assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("(WithInvalidAge, Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidAge() {
        final AbstractBeanAssert<?, User> assertion
                = ValidationAssertions.assertBean(User.newInstanceWithInvalidAge());
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValid(cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty();
    }
}
