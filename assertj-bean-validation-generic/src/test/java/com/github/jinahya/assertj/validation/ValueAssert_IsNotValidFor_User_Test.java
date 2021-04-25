package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValueAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ValueAssertions.assertValue;
import static com.github.jinahya.assertj.validation.ValueWrapper.value;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsNotValidFor_User_Test {

    private static IntStream validAges() {
        return IntStream.range(0, 16).map(i -> User.newValidAge());
    }

    private static Stream<String> validNames() {
        return IntStream.range(0, 16).mapToObj(i -> User.newValidName());
    }

    private static IntStream invalidAges() {
        return IntStream.range(0, 16).map(i -> User.newInvalidAge());
    }

    private static Stream<String> invalidNames() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInvalidName());
    }

    @DisplayName("assertValue(validAge).isNotValidFor(User.class, \"age\") throws AssertionError")
    @ParameterizedTest
    @MethodSource({"validAges"})
    void isNotValidFor_Fail_ValidAge(final int validAge) {
        final ValueAssert valueAssert = assertThat(value(validAge));
        assertThatThrownBy(() -> valueAssert.isNotValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(validName).isNotValidFor(User.class, \"name\") throws AssertionError")
    @ParameterizedTest
    @MethodSource({"validNames"})
    void isNotValidFor_Fail_ValidName(final String validName) {
        final ValueAssert valueAssert = assertValue(validName);
        assertThatThrownBy(() -> valueAssert.isNotValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(invalidAge).isNotValidFor(User.class, \"age\") does not throw any exceptions")
    @ParameterizedTest
    @MethodSource({"invalidAges"})
    void isNotValidFor_Succeed_InvalidAge(final int invalidAge) {
        final ValueAssert valueAssert = assertValue(invalidAge);
        assertThatCode(() -> valueAssert.isNotValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidName).isNotValidFor(User.class, \"name\") does not throw any exceptions")
    @ParameterizedTest
    @MethodSource({"invalidNames"})
    void isNotValidFor_Succeed_InvalidName(final String invalidName) {
        final ValueAssert valueAssert = assertThat(value(invalidName));
        assertThatCode(() -> valueAssert.isNotValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }
}