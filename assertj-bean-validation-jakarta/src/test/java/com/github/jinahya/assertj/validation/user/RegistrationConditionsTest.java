package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-jakarta
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