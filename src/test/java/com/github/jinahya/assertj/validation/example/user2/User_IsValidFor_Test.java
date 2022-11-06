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
import com.github.jinahya.assertj.validation.example.user.User;
import com.github.jinahya.assertj.validation.example.user.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class User_IsValidFor_Test {

    @Nested
    class AgeTest {

        @Test
        void __AgeIsValidForJunior() {
            final var age = UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE - 1;
            final var assertion = assertThatProperty(age);
            assertion.consumingViolations(cv -> {
                        log.debug("cv: {}", cv);
                    })
                    .isValidFor(User2.class, User.PROPERTY_NAME_AGE);
            assertion.consumingViolations(cv -> {
                        log.debug("cv: {}", cv);
                    })
                    .targetingGroups(Junior.class).isValidFor(User2.class, User.PROPERTY_NAME_AGE);
            assertThatThrownBy(
                    () -> assertion
                            .consumingViolations(cv -> {
                                log.debug("cv: {}", cv);
                            })
                            .targetingGroups(Senior.class)
                            .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            )
                    .isInstanceOf(AssertionError.class);
            assertThatThrownBy(
                    () -> assertion
                            .consumingViolations(cv -> {
                                log.debug("cv: {}", cv);
                            })
                            .targetingGroups(Junior.class, Senior.class)
                            .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            )
                    .isInstanceOf(AssertionError.class);
        }

        @Test
        void __AgeIsValidForSenior() {
            final var age = UserConstants.MIN_AGE_FOR_SENIOR_INCLUSIVE;
            final var assertion = assertThatProperty(age);
            assertion
                    .consumingViolations(cv -> {
                        log.debug("cv: {}", cv);
                    })
                    .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            ;
            assertion.targetingGroups(Senior.class)
                    .consumingViolations(cv -> {
                        log.debug("cv: {}", cv);
                    })
                    .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            ;
            assertThatThrownBy(
                    () -> assertion
                            .consumingViolations(cv -> {
                                log.debug("cv: {}", cv);
                            })
                            .targetingGroups(Junior.class)
                            .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            )
                    .isInstanceOf(AssertionError.class)
            ;
            assertThatThrownBy(
                    () -> assertion
                            .consumingViolations(cv -> {
                                log.debug("cv: {}", cv);
                            })
                            .targetingGroups(Junior.class, Senior.class)
                            .isValidFor(User2.class, User.PROPERTY_NAME_AGE)
            )
                    .isInstanceOf(AssertionError.class)
            ;
        }
    }
}
