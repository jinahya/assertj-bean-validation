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

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintViolation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConstraintViolationAssert_HasInvalidValue_Test {

    @Test
    void __NameIsInvalid() {
        final var user = User.newInstance(false, true);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .isValid(i -> {
                            assertThat(i).singleElement().satisfies(cv -> {
                                assertThatConstraintViolation(cv).hasInvalidValue(user.getName());
                            });
                        })
        )
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __AgeIsInvalid() {
        final var user = User.newInstance(true, false);
        assertThatThrownBy(() -> assertThatBean(user)
                .isValid(i -> {
                }))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void __BothAreInvalid() {
        final var user = User.newInstance(false, false);
        assertThatThrownBy(
                () -> assertThatBean(user)
                        .isValid(i -> {
                        })
        )
                .isInstanceOf(AssertionError.class);
    }
}
