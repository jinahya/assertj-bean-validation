package com.github.jinahya.assertj.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserBeanValidationAssertTest {

    static Stream<User> validInstances() {
        return IntStream.rangeClosed(0, current().nextInt(128))
                .mapToObj(i -> User.newValidInstance());
    }

    static Stream<User> invalidInstances() {
        return IntStream.rangeClosed(0, current().nextInt(128))
                .mapToObj(i -> User.newInvalidInstance());
    }

    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid_Succeed_(final User user) {
        assertBean(user).isValid();
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isValid_Fail_(final User user) {
        assertThrows(AssertionError.class, () -> assertBean(user).isValid());
    }
}
