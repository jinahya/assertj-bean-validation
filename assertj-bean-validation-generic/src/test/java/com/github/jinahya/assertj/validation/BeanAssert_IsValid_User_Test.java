package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;

class BeanAssert_IsValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(1, 16).mapToObj(i -> User.newValidInstance());
    }

    @MethodSource({"validUsers"})
    @ParameterizedTest
    void isValid__(final User user) {
        final BeanAssert beanAssert = BeanAssertions.assertThat(bean(user));
        beanAssert.isValid();
    }
}