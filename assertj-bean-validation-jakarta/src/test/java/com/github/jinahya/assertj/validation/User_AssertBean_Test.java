package com.github.jinahya.assertj.validation;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class User_AssertBean_Test {

    @DisplayName("assertBean(actual)")
    @Test
    void assertBean__() {
        final User actual = User.newValidInstance();
        final BeanAssert<?, User> assertion = ValidationAssertions.assertThatBean(actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("assertBean(selfClass, actualClass, actual)")
    @Test
    void assertBeanWithSelfClassAndActualClass__() {
        final User actual = User.newValidInstance();
        actual.setName("Jane");
        final UserAssert assertion = ValidationAssertions.assertThatBean(UserAssert.class, User.class, actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }

    @DisplayName("assertBean(selfClass, actual)")
    @Test
    void assertBeanWithSelfClass__() {
        final User actual = User.newValidInstance();
        actual.setName("Jane");
        final UserAssert assertion = ValidationAssertions.assertThatBean(UserAssert.class, actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }

    @DisplayName("assertVirtualBean(actual)")
    @Test
    void assertVirtualBean__() {
        final User actual = User.newValidInstance();
        actual.setName("Jane");
        final UserAssert assertion = ValidationAssertions.assertThatVirtualBean(actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }
}
