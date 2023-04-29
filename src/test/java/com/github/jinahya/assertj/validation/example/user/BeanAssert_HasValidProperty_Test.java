package com.github.jinahya.assertj.validation.example.user;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 - 2023 Jinahya, Inc.
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

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanAssert_HasValidProperty_Test {

    @Test
    void hasValidProperty__() {
        {
            final var user = User.newInstance(true, true);
            final var assertion = assertThatBean(user);
            assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME))
                    .doesNotThrowAnyException();
            assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE))
                    .doesNotThrowAnyException();
        }
        {
            final var user = User.newInstance(false, true);
            final var assertion = assertThatBean(user);
            assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME))
                    .isInstanceOf(AssertionError.class);
            assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE))
                    .doesNotThrowAnyException();
        }
        {
            final var user = User.newInstance(true, false);
            final var assertion = assertThatBean(user);
            assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME))
                    .doesNotThrowAnyException();
            assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE))
                    .isInstanceOf(AssertionError.class);
        }
        {
            final var user = User.newInstance(false, false);
            final var assertion = assertThatBean(user);
            assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME))
                    .isInstanceOf(AssertionError.class);
            assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE))
                    .isInstanceOf(AssertionError.class);
        }
    }
}
