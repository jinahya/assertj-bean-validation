package com.github.jinahya.assertj.validation.example.user;

/*-
 * #%L
 * assertj-bean-validation
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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PropertyAssert_IsValidFor_Test {

    @Nested
    class RegistrationTest {

        @Test
        void _ShouldFail_Null() {
            final var assertion = assertProperty(null);
            assertThatThrownBy(() -> assertion.isValidFor(Registration.class, "user"))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void _ShouldPass_InvalidNameInvalidAge() {
            final var user = User.newInstance(false, false);
            {
                final var assertion = assertBean(user);
                assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(Registration.class, "user"))
                    .doesNotThrowAnyException();
        }

        @Test
        void _ShouldFail_ValidNameValidAge() {
            final var user = User.newInstance(true, true);
            {
                final var assertion = assertBean(user);
                assertThatCode(assertion::isValid).doesNotThrowAnyException();
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(Registration.class, "user"))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class JuniorRegistrationTest {

        @Test
        void _ShouldFail_Null() {
            final var assertion = assertProperty(null);
            assertThatThrownBy(() -> assertion.isValidFor(JuniorRegistration.class, "user"))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void _ShouldPass_InvalidNameValidAgeFor() {
            final var user = User.newInstance(false, false);
            user.setAge(UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE - 1);
            {
                final var assertion = assertBean(user);
                assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(JuniorRegistration.class, "user"))
                    .doesNotThrowAnyException();
        }

        @Test
        void _ShouldFail_InvalidNameInvalidAgeFor() {
            final var user = User.newInstance(false, false);
            user.setAge(UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE);
            {
                final var assertion = assertBean(user);
                assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            }
            final var assertion = assertProperty(user);
            assertThatThrownBy(() -> assertion.isValidFor(JuniorRegistration.class, "user"))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void _ShouldPass_ValidNameValidAgeFor() {
            final var user = User.newInstance(true, true);
            user.setAge(UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE - 1);
            {
                final var assertion = assertBean(user);
                assertThatCode(assertion::isValid).doesNotThrowAnyException();
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(Registration.class, "user"))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class SeniorRegistrationTest {

        @Test
        void _ShouldFail_Null() {
            final var assertion = assertProperty(null);
            assertThatThrownBy(() -> assertion.isValidFor(SeniorRegistration.class, "user"))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void _ShouldPass_InvalidNameValidAgeFor() {
            final var user = User.newInstance(false, false);
            user.setAge(UserConstants.MIN_AGE_FOR_SENIOR_INCLUSIVE);
            {
                final var assertion = assertBean(user);
                assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(SeniorRegistration.class, "user"))
                    .doesNotThrowAnyException();
        }

        @Test
        void _ShouldFail_InvalidNameInvalidAgeFor() {
            final var user = User.newInstance(false, false);
            user.setAge(UserConstants.MIN_AGE_FOR_SENIOR_INCLUSIVE - 1);
            {
                final var assertion = assertBean(user);
                assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            }
            final var assertion = assertProperty(user);
            assertThatThrownBy(() -> assertion.isValidFor(SeniorRegistration.class, "user"))
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void _ShouldPass_ValidNameValidAgeFor() {
            final var user = User.newInstance(true, true);
            user.setAge(UserConstants.MIN_AGE_FOR_SENIOR_INCLUSIVE);
            {
                final var assertion = assertBean(user);
                assertThatCode(assertion::isValid).doesNotThrowAnyException();
            }
            final var assertion = assertProperty(user);
            assertThatCode(() -> assertion.isValidFor(Registration.class, "user"))
                    .doesNotThrowAnyException();
        }
    }
}
