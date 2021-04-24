package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanAssert_IsValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(0, 1).mapToObj(i -> User.newValidInstance());
    }

    private static Stream<User> invalidUsers() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInvalidInstance());
    }

    @DisplayName("assertBean(valid).isValid() does not throw any exception")
    @ParameterizedTest
    @MethodSource({"validUsers"})
    void isValid_Succeed_Valid(final User user) {
        final BeanAssert beanAssert = BeanAssertions.assertThat(bean(user));
        assertThatCode(beanAssert::isValid)
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBean(invalid).isValid() throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"invalidUsers"})
    void isValid_Fail_Invalid(final User user) {
        final BeanAssert beanAssert = BeanAssertions.assertThat(bean(user));
        assertThatThrownBy(beanAssert::isValid)
                .isInstanceOf(AssertionError.class);
    }
}