package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static com.github.jinahya.assertj.validation.ConstraintViolationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintViolationWrapper.constraintViolation;
import static com.github.jinahya.assertj.validation.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.PathWrapper.path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_IsNotValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(0, 16).mapToObj(i -> User.newValidInstance());
    }

    private static Stream<User> invalidUsers() {
        return IntStream.range(0, 1).mapToObj(i -> User.newInvalidInstance());
    }

    private static Stream<User> usersWithInvalidAge() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    private static Stream<User> usersWithInvalidName() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidName());
    }

    @DisplayName("assertBean(valid).isNotValid() throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"validUsers"})
    void isNotValid_Fail_Valid(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
        // then
        assertThatThrownBy(beanAssert::isNotValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertBean(invalid).isNotValid() does not throw any exception")
    @ParameterizedTest
    @MethodSource({"invalidUsers"})
    void isNotValid_Succeed_Invalid(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
        // then
        assertThatCode(beanAssert::isNotValid)
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @MethodSource({"invalidUsers"})
    void isNotValid_NonEmptyConstraintViolations_Invalid(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).isNotEmpty().allSatisfy(v -> {
                assertThat(constraintViolation(v))
                        .hasInvalidValueSatisfying(i -> {
                            assertThat(i).isNotNull();
                        })
                        .hasLeafBeanSameAs(user)
                        .hasMessageSatisfying(m -> {
                            assertThat(m).isNotBlank();
                        })
                        .hasPropertyPathSatisfying(p -> {
                            assertThat(path(p)).isNotNull()
                            ;
                        })
                        .hasRootBeanClassSameAs(User.class)
                ;
            });
        });
    }

    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @MethodSource({"usersWithInvalidAge"})
    void isNotValid_NonEmptyConstraintViolations_InvalidAge(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).isNotEmpty().allSatisfy(v -> {
                assertThat(constraintViolation(v))
                        .hasInvalidValueEqualTo(user.getAge())
                        .hasLeafBeanSameAs(user)
                        .hasMessageSatisfying(m -> {
                            assertThat(m).isNotBlank();
                        })
                        .hasPropertyPathSatisfying(p -> {
                            assertThat(path(p)).isNotNull();
                            assertThat(p).isNotNull();
                        })
                        .hasRootBeanClassSameAs(User.class)
                ;
            });
        });
    }
}