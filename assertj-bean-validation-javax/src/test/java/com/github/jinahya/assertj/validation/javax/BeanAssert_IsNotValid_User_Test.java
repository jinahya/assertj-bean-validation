package com.github.jinahya.assertj.validation.javax;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanAssert_IsNotValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(0, 16).mapToObj(i -> User.newValidInstance());
    }

    private static Stream<User> invalidUsers() {
        return IntStream.range(0, 1).mapToObj(i -> User.newInvalidInstance());
    }

    @DisplayName("assertBean(valid).isNotValid() throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"validUsers"})
    void isNotValid_Fail_Valid(final User user) {
        final BeanAssert<User> beanAssert = BeanAssertions.assertThat(bean(user));
        assertThatThrownBy(beanAssert::isNotValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertBean(invalid).isNotValid() does not throw any exception")
    @ParameterizedTest
    @MethodSource({"invalidUsers"})
    void isNotValid_Succeed_Invalid(final User user) {
        final BeanAssert<User> beanAssert = BeanAssertions.assertThat(bean(user));
        assertThatCode(beanAssert::isNotValid)
                .doesNotThrowAnyException();
    }
}