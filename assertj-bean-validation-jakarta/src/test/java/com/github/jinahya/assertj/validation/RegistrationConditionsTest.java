package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegistrationConditionsTest {

    @Test
    void SENIOR_USER_Fail_() {
        final var user = User.of("None", 59);
        final var registration = Registration.of(user);
        assertThatThrownBy(() -> assertThat(registration).has(RegistrationConditions.SENIOR_USER))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void SENIOR_USER_Pass_() {
        final var user = User.of("None", 60);
        final var registration = Registration.of(user);
        assertThatCode(() -> assertThat(registration).has(RegistrationConditions.SENIOR_USER))
                .doesNotThrowAnyException();
    }
}