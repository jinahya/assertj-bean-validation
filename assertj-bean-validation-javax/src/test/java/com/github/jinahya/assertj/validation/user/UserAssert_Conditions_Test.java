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

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class UserAssert_Conditions_Test
        extends UserAssertTest {

    @Test
    void isNamedJane__() {
        final User actual = User.newValidInstance_().name("Jane").build();
        assertInstant(actual)
                .isNamedJane();
    }

    @Test
    void isNamedJane_Fail_John() {
        final User actual = User.newValidInstance_().name("John").build();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(assertion::isNamedJane)
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void isNamedJohn__() {
        final User actual = User.newValidInstance_().name("John").build();
        assertInstant(actual)
                .isNamedJohn();
    }

    @Test
    void isNamedJohn_Fail_Jane() {
        final User actual = User.newValidInstance_().name("Jane").build();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(assertion::isNamedJohn)
                .isInstanceOf(AssertionError.class);
    }
}
