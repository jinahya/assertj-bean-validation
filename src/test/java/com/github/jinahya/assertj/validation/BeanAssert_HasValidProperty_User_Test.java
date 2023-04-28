package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_HasValidProperty_User_Test {

    private static Stream<Arguments> flags() {
        return Stream.of(true, false)
                .flatMap(vn -> Stream.of(true, false).map(va -> Arguments.of(vn, va)));
    }

    @MethodSource({"flags"})
    @ParameterizedTest(name = "[{index}] validName: {0}, validAge: {1}")
    void __(final boolean validName, final boolean validAge) {
        final var bean = User.newInstance(validName, validAge);
        final var assertion = assertThatBean(bean);
        if (true) {
            assertion.usingValidator(Validation.buildDefaultValidatorFactory().getValidator());
        }
        if (validName) {
            assertThatCode(() -> assertion.hasValidProperty("name"))
                    .doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> assertion.hasValidProperty("name"))
                    .isInstanceOf(AssertionError.class)
                    .satisfies(ar -> {
                        log.debug("message: {}", ar.getMessage());
                    });
        }
        if (validAge) {
            assertThatCode(() -> assertion.hasValidProperty("age"))
                    .doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> assertion.hasValidProperty("age"))
                    .isInstanceOf(AssertionError.class)
                    .satisfies(ar -> {
                        log.debug("message: {}", ar.getMessage());
                    });
        }
    }
}
