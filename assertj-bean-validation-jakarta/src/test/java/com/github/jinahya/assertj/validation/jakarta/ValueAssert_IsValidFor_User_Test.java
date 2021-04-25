package com.github.jinahya.assertj.validation.jakarta;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValueWrapper.value;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsValidFor_User_Test {

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

    @DisplayName("assertValue(validAge).isValueFor(User.class, \"age\") does not throw any exception")
    @ParameterizedTest
    @MethodSource({"validAges"})
    void isValidFor_Succeed_ValidAge(final int validAge) {
        final ValueAssert valueAssert = ValueAssertions.assertThat(value(validAge));
        assertThatCode(() -> valueAssert.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(validName).isValueFor(User.class, \"name\") does not throw any exception")
    @ParameterizedTest
    @MethodSource({"validNames"})
    void isValidFor_Succeed_ValidName(final String validName) {
        final ValueAssert valueAssert = ValueAssertions.assertValue(validName);
        assertThatCode(() -> valueAssert.isValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidAge).isValueFor(User.class, \"age\") throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"invalidAges"})
    void isValidFor_Fail_InvalidAge(final int invalidAge) {
        final ValueAssert valueAssert = ValueAssertions.assertValue(invalidAge);
        assertThatThrownBy(() -> valueAssert.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(invalidName).isValueFor(User.class, \"name\") throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"invalidNames"})
    void isValidFor_Fail_InvalidName(final String invalidName) {
        final ValueAssert valueAssert = ValueAssertions.assertThat(value(invalidName));
        assertThatThrownBy(() -> valueAssert.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }
}