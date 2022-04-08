package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-javax
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.assertj.validation.AbstractPropertyAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Slf4j
class IsValidFor_RegistrationUser_Test {

    @DisplayName("[Valid] isValidFor(Registration.class, \"user\") should pass")
    @MethodSource("com.github.jinahya.assertj.validation.user.UserTests#validUserStream")
    @ParameterizedTest
    void isValidFor_Pass_Valid(final User actual) {
        final AbstractPropertyAssert<?, User> assertion = ValidationAssertions.assertProperty(actual);
        assertion.isValidFor(Registration.class, "user");
    }

    @DisplayName("[Valid] isValidFor(Registration.class, \"user\") should pass")
    @MethodSource("com.github.jinahya.assertj.validation.user.UserTests#validUserStream")
    @ParameterizedTest
    void isValidForWithConsumer_Pass_Valid(final User actual) {
        final AbstractPropertyAssert<?, User> assertion = ValidationAssertions.assertProperty(actual);
        final Set<ConstraintViolation<Registration>> violations = new HashSet<>();
        assertion.isValidFor(Registration.class, "user", violations::add);
        Assertions.assertThat(violations)
                .isEmpty();
    }

    @DisplayName("[Null] isValidFor(Registration.class, \"user\") should fail")
    @Test
    void isValidFor_Fail_Null() {
        final User actual = null;
        final AbstractPropertyAssert<?, User> assertion = ValidationAssertions.assertProperty(actual);
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(Registration.class, "user"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[Invalid] isValidFor(Registration.class, \"user\") should fail")
    @MethodSource("com.github.jinahya.assertj.validation.user.UserTests#invalidUserStream")
    @ParameterizedTest
    void isValidFor_Fail_Invalid(final User actual) {
        final AbstractPropertyAssert<?, User> assertion = ValidationAssertions.assertProperty(actual);
        Assertions.assertThatCode(() -> assertion.isValidFor(Registration.class, "user"))
                .doesNotThrowAnyException(); // @Valid is not honored by validateValue
    }

    @DisplayName("[Invalid] isValidFor(Registration.class, \"user\", Consumer) should fail")
    @MethodSource("com.github.jinahya.assertj.validation.user.UserTests#invalidUserStream")
    @ParameterizedTest
    void isValidForWithConsumer_Fail_Invalid(final User actual) {
        final AbstractPropertyAssert<?, User> assertion = ValidationAssertions.assertProperty(actual);
        final Set<ConstraintViolation<Registration>> violations = new HashSet<>();
        Assertions.assertThatCode(() -> assertion.isValidFor(Registration.class, "user", violations::add))
                .doesNotThrowAnyException(); // @Valid is not honored by validateValue
        Assertions.assertThat(violations)
                .isEmpty();
    }
}
