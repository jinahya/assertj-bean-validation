package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatIterableConstraintViolations;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConstraintViolationAssert_User_Test {

    private static Stream<Arguments> flags() {
        return Stream.of(true, false)
                .flatMap(vn -> Stream.of(true, false).map(va -> Arguments.of(vn, va)));
    }

    @MethodSource({"flags"})
    @ParameterizedTest(name = "[{index}] validName: {0}, validAge: {1}")
    void __(final boolean validName, final boolean validAge) {
        final var bean = User.newInstance(validName, validAge);
        final var assertion = assertThatBean(bean);
        if (!validName || !validAge) {
            assertThatThrownBy(() -> assertion.isValid(a -> {
                final var a2 = assertThatIterableConstraintViolations(a);
            }))
                    .isInstanceOf(AssertionError.class);
        }
    }
}
