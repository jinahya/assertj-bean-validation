package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
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
            assertThatThrownBy(assertion::isNotValid)
                    .isInstanceOf(AssertionError.class)
                    .satisfies(ae -> {
                        log.debug("message: {}", ae.getMessage());
                    });
            return;
        }
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
        assertThatCode(assertion::isNotValid).doesNotThrowAnyException();
    }
}
