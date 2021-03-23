package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.BeanValidationAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanValidationTestUtils.validator;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    static Stream<String> validNames() {
        return Stream.of(Long.toString(System.nanoTime()));
    }

    static Stream<String> invalidNames() {
        return Stream.of(null, "", " ");
    }

    static IntStream validAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() >>> 1);
    }

    static IntStream invalidAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() | Integer.MIN_VALUE);
    }

    static Stream<User> validInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newValidInstance());
    }

    static Stream<User> instancesWithInvalidName() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidName());
    }

    static Stream<User> instancesWithInvalidAge() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    static Stream<User> invalidInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInvalidInstance());
    }

    // --------------------------------------------------------------------------------------------------------- isValid
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid_Succeed_Valid(final User user) {
        assertBean(user).isValid();
        assertThat(bean(user)).isValid();
        assertBean(user).using(validator()).isValid();
        assertThat(bean(user)).using(validator()).isValid();
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isValid_Fail_Invalid(final User user) {
        final BeanValidationAssert a = assertBean(user);
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
        assertThat(a.using(validator())).isNotNull();
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
    }

    // ------------------------------------------------------------------------------------------------ hasValidProperty
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void hasValidProperty_Succeed_Valid(final User user) {
        assertBean(user).hasValidProperty("name");
        assertBean(user).hasValidProperty("age");
        assertThat(bean(user)).hasValidProperty("name");
        assertThat(bean(user)).hasValidProperty("age");
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void hasValidProperty_Fail_InvalidName(final User userWithInvalidName) {
        final BeanValidationAssert a = assertBean(userWithInvalidName);
        assertThatThrownBy(() -> a.hasValidProperty("name")).isInstanceOf(AssertionError.class);
        a.hasValidProperty("age");
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void hasValidProperty_Fail_InvalidAge(final User userWithInvalidAge) {
        final BeanValidationAssert a = assertBean(userWithInvalidAge);
        assertThatThrownBy(() -> a.hasValidProperty("age")).isInstanceOf(AssertionError.class);
        a.hasValidProperty("name");
    }
}
