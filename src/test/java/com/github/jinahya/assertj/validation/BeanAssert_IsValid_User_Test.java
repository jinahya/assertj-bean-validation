package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintViolation;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatIterableOfConstraintViolations;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_IsValid_User_Test {

    private static Stream<Arguments> flags() {
        return Stream.of(true, false)
                .flatMap(vn -> Stream.of(true, false).map(va -> Arguments.of(vn, va)));
    }

    @MethodSource({"flags"})
    @ParameterizedTest(name = "[{index}] validName: {0}, validAge: {1}")
    void __(final boolean validName, final boolean validAge) {
        final var user = User.newInstance(validName, validAge);
        final var assertion = assertThatBean(user);
        if (validName && validAge) {
            assertThatCode(assertion::isValid).doesNotThrowAnyException();
            return;
        }
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @Test
    void __NameIsInvalid() {
        final var user = User.newInstance(false, true);
        final var assertion = assertThatBean(user);
        assertThatThrownBy(
                () -> assertion.isValid(
                        i -> {
                            assertThatIterableOfConstraintViolations(i)
                                    .singleElement()
                                    .hasRootBeanClass(User.class)
                                    .hasRootBean(user)
                                    .hasInvalidValue(user.getName());
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @Test
    void __AgeIsInvalid() {
        final var user = User.newInstance(true, false);
        final var assertion = assertThatBean(user);
        assertThatThrownBy(
                () -> assertion.isValid(
                        i -> {
                            assertThatIterableOfConstraintViolations(i)
                                    .singleElement()
                                    .hasRootBeanClass(User.class)
                                    .hasRootBean(user)
                                    .hasInvalidValue(user.getAge());
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @Test
    void __BothAreInvalid() {
        final var bean = User.newInstance(false, false);
        final var assertion = assertThatBean(bean);
        assertThatThrownBy(() -> assertion.isValid(
                i -> {
                    assertThatIterableOfConstraintViolations(i).allSatisfy(cv -> {
                        assertThatConstraintViolation(cv)
                                .hasRootBeanClass(User.class)
                                .hasRootBean(bean);
                    });
                }
        ))
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }
}
