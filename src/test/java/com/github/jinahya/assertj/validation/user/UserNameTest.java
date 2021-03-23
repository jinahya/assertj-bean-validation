package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.BeanPropertyValidationAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertBeanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserNameTest {

    private static Stream<String> invalidNames() {
        return UserTest.invalidNames();
    }

    private static Stream<String> validNames() {
        return UserTest.validNames();
    }

    @MethodSource({"invalidNames"})
    @ParameterizedTest
    void isValidFor_Fail_InvalidName(final String invalidName) {
        final BeanPropertyValidationAssert a = assertBeanProperty(invalidName);
        assertThatThrownBy(() -> a.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"validNames"})
    @ParameterizedTest
    void isValidFor_Succeed_ValidName(final String validName) {
        final BeanPropertyValidationAssert a = assertBeanProperty(validName);
        assertThatCode(() -> a.isValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }
}
