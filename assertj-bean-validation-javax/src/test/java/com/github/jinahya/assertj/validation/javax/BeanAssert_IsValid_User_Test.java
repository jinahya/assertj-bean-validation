package com.github.jinahya.assertj.validation.javax;

import com.github.jinahya.assertj.validation.user.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class BeanAssert_IsValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(1, 2).mapToObj(i -> User.newValidInstance());
    }

    @MethodSource({"validUsers"})
    @ParameterizedTest
    void isValid__(final User user) {
//        final BeanAssertImpl<User> beanAssert = ValidationAssertions.assertThatBean(bean(user));
//        beanAssert.isValid();
    }
}