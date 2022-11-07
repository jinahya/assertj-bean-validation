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

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertBean;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JuniorRegistration_IsValid_Test {

    @Test
    void __UserValid() {
        final var registration = new JuniorRegistration(User.newInstance(true, true));
        registration.getUser().setAge(UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE - 1);
        assertBean(registration)
                .isValid();
    }

    @Test
    void __UserNull() {
        final var registration = new JuniorRegistration(null);
        final var assertion = assertBean(registration);
        assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
    }

    @Test
    void __UserInvalid() {
        final var registration = new JuniorRegistration(User.newInstance(false, false));
        final var assertion = assertBean(registration);
        assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
    }
}
