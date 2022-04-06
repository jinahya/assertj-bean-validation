package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.AbstractValueAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Slf4j
class UserValueAssert_IsValidFor_Test {

    @DisplayName("[ValidName] isValidFor(\"name\") should pass")
    @Test
    void isValidForName_Pass_Valid() {
        final String actual = User.newValidName();
        final AbstractValueAssert<?, String> assertion = ValidationAssertions.assertValue(actual);
        assertion.isValidFor(User.class, "name");
    }

    @DisplayName("[ValidName] isValidFor(\"name\", Consumer) should pass")
    @Test
    void isValidForNameConsumer_Pass_Valid() {
        final String actual = User.newValidName();
        final AbstractValueAssert<?, String> assertion = ValidationAssertions.assertValue(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        assertion.isValidFor(User.class, "name", violations::add);
        Assertions.assertThat(violations).isEmpty();
    }

    @DisplayName("[InvalidName] isValidFor(User.class, \"name\") should fail")
    @Test
    void isValidForName_Fail_InvalidName() {
        final String actual = User.newInvalidName();
        final AbstractValueAssert<?, String> assertion = ValidationAssertions.assertValue(actual);
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidName] isValidFor(User.class, \"name\", Consumer) should fail")
    @Test
    void isValidForNameConsumer_Fail_InvalidName() {
        final String actual = User.newInvalidName();
        final AbstractValueAssert<?, String> assertion = ValidationAssertions.assertValue(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "name", violations::add))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual);
                    Assertions.assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("name");
                            });
                });
    }

    @DisplayName("[Valid] isValidFor(User.class, \"age\") should pass")
    @Test
    void isValidForAge_Pass_Valid() {
        final int actual = User.newValidAge();
        final AbstractValueAssert<?, Integer> assertion = ValidationAssertions.assertValue(actual);
        assertion.isValidFor(User.class, "age");
    }

    @DisplayName("[InvalidAge] isValidFor(User.class, \"age\") should fail")
    @Test
    void isValidForAge_Fail_InvalidAge() {
        final int actual = User.newInvalidAge();
        final AbstractValueAssert<?, Integer> assertion = ValidationAssertions.assertValue(actual);
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidAge] isValidFor(User.class, \"age\", Consumer) should fail")
    @Test
    void isValidForAgeConsumer_Fail_InvalidAge() {
        final int actual = User.newInvalidAge();
        final AbstractValueAssert<?, Integer> assertion = ValidationAssertions.assertValue(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "age", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty();
    }
}
