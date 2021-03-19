package com.github.jinahya.assertj.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertThat;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserBeanValidationAssertTest {

    static Stream<BeanWrapper<User>> validInstances() {
        return IntStream.rangeClosed(0, current().nextInt(128))
                .mapToObj(i -> User.newValidInstance())
                .map(BeanWrapper::bean);
    }

    static Stream<BeanWrapper<User>> invalidInstances() {
        return IntStream.rangeClosed(0, current().nextInt(128))
                .mapToObj(i -> User.newInvalidInstance())
                .map(BeanWrapper::bean);
    }

    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid_Succeed_(final BeanWrapper<User> user) {
        assertThat(user).isValid();
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isValid_Fail_(final BeanWrapper<User> user) {
        assertThatThrownBy(() -> assertThat(user).isValid())
                .isInstanceOf(AssertionError.class);
    }
}
