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
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.PropertyAssertCompanion.isNotValidFor;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PropertyAssertCompanion_IsValidFor_Test {

    @Test
    void __ValidName() {
        {
            final var name = User.validName();
            final var assertion = assertThatProperty(name);
            assertThatThrownBy(() -> isNotValidFor(assertion, User.class, User.PROPERTY_NAME_NAME))
                    .isInstanceOf(AssertionError.class);
        }
    }

    @Test
    void __InvalidName() {
        {
            final var name = User.invalidName();
            final var assertion = assertThatProperty(name);
            assertThatCode(() -> isNotValidFor(assertion, User.class, User.PROPERTY_NAME_NAME))
                    .doesNotThrowAnyException();
        }
    }

    @Test
    void __ValidAge() {
        {
            final var age = User.validAge();
            final var assertion = assertThatProperty(age);
            assertThatThrownBy(() -> isNotValidFor(assertion, User.class, User.PROPERTY_NAME_AGE))
                    .isInstanceOf(AssertionError.class);
        }
    }

    @Test
    void __InvalidAge() {
        {
            final var age = User.invalidAge();
            final var assertion = assertThatProperty(age);
            assertThatCode(() -> isNotValidFor(assertion, User.class, User.PROPERTY_NAME_AGE))
                    .doesNotThrowAnyException();
        }
    }
}
