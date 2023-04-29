package com.github.jinahya.assertj.validation.example.user2;

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

import com.github.jinahya.assertj.validation.example.user.Junior;
import com.github.jinahya.assertj.validation.example.user.Senior;
import com.github.jinahya.assertj.validation.example.user.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
@Slf4j
class User2_IsValid_Test {

    @Nested
    class TargetingJuniorTest {

        @Test
        void __AgeIsValidForJunior() {
            final User2 bean = User2.newInstance(true, true);
            bean.setAge(UserConstants.MAX_AGE_FOR_JUNIOR);
            {
                assertThatCode(() -> assertThatBean(bean).isValid()).doesNotThrowAnyException();
            }
            final var assertion = assertThatBean(bean);
            assertion.targetingGroups(Junior.class);
            assertThatCode(assertion::isValid).doesNotThrowAnyException();
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class, Junior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
        }

        @Test
        void __AgeIsInvalidForJunior() {
            final User2 bean = User2.newInstance(true, true);
            bean.setAge(UserConstants.MAX_AGE_FOR_JUNIOR);
            {
                assertThatCode(() -> assertThatBean(bean).isValid()).doesNotThrowAnyException();
            }
            final var assertion = assertThatBean(bean);
            assertion.targetingGroups(Junior.class);
            assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            {
                assertThatCode(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class)
                                .isValid()
                )
                        .doesNotThrowAnyException();
            }
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class, Junior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
        }
    }

    @Nested
    class TargetingSeniorTest {

        @Test
        void __AgeIsValidForSenior() {
            final User2 bean = User2.newInstance(true, true);
            bean.setAge(UserConstants.MIN_AGE_FOR_SENIOR);
            {
                assertThatCode(() -> assertThatBean(bean).isValid()).doesNotThrowAnyException();
            }
            final var assertion = assertThatBean(bean);
            assertion.targetingGroups(Senior.class);
            assertThatCode(assertion::isValid).doesNotThrowAnyException();
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Junior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class, Junior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
        }

        @Test
        void __AgeIsInvalidForSenior() {
            final User2 bean = User2.newInstance(true, true);
            bean.setAge(UserConstants.MIN_AGE_FOR_SENIOR - 1);
            {
                assertThatCode(() -> assertThatBean(bean).isValid()).doesNotThrowAnyException();
            }
            final var assertion = assertThatBean(bean);
            assertion.targetingGroups(Senior.class);
            assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
            {
                assertThatCode(
                        () -> assertThatBean(bean)
                                .targetingGroups(Junior.class)
                                .isValid()
                )
                        .doesNotThrowAnyException();
            }
            {
                assertThatThrownBy(
                        () -> assertThatBean(bean)
                                .targetingGroups(Senior.class, Junior.class)
                                .isValid()
                )
                        .isInstanceOf(AssertionError.class);
            }
        }
    }
}
