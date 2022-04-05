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
class UserAssert_HasValidProperty_Test
        extends UserAssertTest {

    @DisplayName("(Valid) should pass")
    @Test
    void _Pass_Valid() {
        final User actual = User.newValidInstance();
        final UserAssert assertion = assertInstant(actual);
        assertion
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("(WithInvalidName) should fail")
    @Test
    void isValid_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("(WithInvalidName, Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("name", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getPropertyPath()).allSatisfy(n -> {
                        Assertions.assertThat(n.getName())
                                .isEqualTo("name");
                    });
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getName());
                });
    }

    @DisplayName("(WithInvalidAge) should fail")
    @Test
    void isValid_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("(WithInvalidAge, Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("age", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getPropertyPath()).allSatisfy(n -> {
                        Assertions.assertThat(n.getName())
                                .isEqualTo("age");
                    });
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getAge());
                });
    }
}
