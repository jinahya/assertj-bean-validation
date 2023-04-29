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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@DisplayName("assertThatBean(JuniorRegistration)")
class JuniorRegistration_IsValid_Test {

    @DisplayName("user is null")
    @Test
    void __UserNull() {
        {
            final var registration = JuniorRegistration.of(null);
            final var assertion = assertThatBean(registration);
            assertThatThrownBy(assertion::isValid)
                    .isInstanceOf(AssertionError.class);
        }
        {
            final var registration = spy(JuniorRegistration.class);
            when(registration.getUser()).thenReturn(null);
            final var assertion = assertThatBean(registration);
            assertThatThrownBy(assertion::isValid)
                    .isInstanceOf(AssertionError.class);
        }
    }

    @DisplayName("user is valid")
    @Test
    void __UserValid() {
        {
            final var registration = JuniorRegistration.of(User.newInstance(true, true));
            registration.getUser().setAge(UserConstants.MAX_AGE_FOR_JUNIOR);
            assertThatBean(registration)
                    .isValid();
        }
        {
            final var user = User.newJunior();
            final var registration = JuniorRegistration.of(user);
            assertThatBean(registration).isValid();
        }
    }

    @DisplayName("user is invalid")
    @Test
    void __UserInvalid() {
        {
            final var registration = JuniorRegistration.of(User.newInstance(false, false));
            final var assertion = assertThatBean(registration);
            assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
        }
        {
            final var user = User.newSenior();
            final var registration = JuniorRegistration.of(user);
            assertThatThrownBy(() -> assertThatBean(registration).isValid())
                    .isInstanceOf(AssertionError.class);
        }
    }
}
