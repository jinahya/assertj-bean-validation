package com.github.jinahya.assertj.validation.user;

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

    @DisplayName("[Valid] hasValidProperty(both) should pass")
    @Test
    void hasValidProperty_Pass_Valid() {
        final User actual = User.newValidInstance();
        final UserAssert assertion = assertInstant(actual);
        assertion
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("[InvalidName] hasValidProperty(\"name\") should fail")
    @Test
    void hasValidPropertyName_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidName] hasValidProperty(\"age\") should pass")
    @Test
    void hasValidPropertyAge_Pass_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatCode(() -> assertion.hasValidProperty("age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("[InvalidName] hasValidProperty(\"name\", Consumer) should fail")
    @Test
    void hasValidPropertyNameConsumer_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
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
                    Assertions.assertThat(cv.getPropertyPath())
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("name");
                            });
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getName());
                });
    }

    @DisplayName("[InvalidName] hasValidProperty(\"age\", Consumer) should pass")
    @Test
    void hasValidPropertyAgeConsumer_Pass_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatCode(() -> assertion.hasValidProperty("age", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .doesNotThrowAnyException();
        Assertions.assertThat(violations)
                .isEmpty();
    }

    @DisplayName("[InvalidAge] hasValidProperty(\"age\") should fail")
    @Test
    void hasValidPropertyAge_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidAge] hasValidProperty(\"name\") should pass")
    @Test
    void hasValidPropertyName_Pass_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatCode(() -> assertion.hasValidProperty("name"))
                .doesNotThrowAnyException();
    }

    @DisplayName("[InvalidAge] hasValidProperty(\"age\", Consumer) should fail")
    @Test
    void hasValidPropertyAgeConsumer_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstant(actual);
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

    @DisplayName("[InvalidAge] hasValidProperty(\"name\", Consumer) should pass")
    @Test
    void hasValidPropertyNameConsumer_Pass_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstant(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatCode(() -> assertion.hasValidProperty("name", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .doesNotThrowAnyException();
        Assertions.assertThat(violations)
                .isEmpty();
    }
}
