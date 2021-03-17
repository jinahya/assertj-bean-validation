package com.github.jinahya.assertj.validation;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;

class UserBeanValidationAssertTest {

    static Stream<User> validInstances() {
        return IntStream.rangeClosed(0, ThreadLocalRandom.current().nextInt(128))
                .mapToObj(i -> User.newValidInstance());
    }

    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid__() {
        assertBean(User.newValidInstance()).isValid();
    }
}
