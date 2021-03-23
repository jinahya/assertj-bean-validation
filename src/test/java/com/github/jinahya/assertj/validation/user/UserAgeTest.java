package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.BeanPropertyValidationAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertBeanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserAgeTest {

    private static IntStream invalidAges() {
        return UserTest.invalidAges();
    }

    private static IntStream validAges() {
        return UserTest.validAges();
    }

    @MethodSource({"invalidAges"})
    @ParameterizedTest
    void isValidFor_Fail_InvalidAge(final int invalidAge) {
        final BeanPropertyValidationAssert a = assertBeanProperty(invalidAge);
        assertThatThrownBy(() -> a.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"validAges"})
    @ParameterizedTest
    void isValidFor_Succeed_ValidAge(final int validAge) {
        final BeanPropertyValidationAssert a = assertBeanProperty(validAge);
        assertThatCode(() -> a.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }
}
