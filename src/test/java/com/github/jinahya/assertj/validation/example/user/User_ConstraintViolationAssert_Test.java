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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintViolation;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldFail;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldPass;
import static com.github.jinahya.assertj.validation.example.user.User.PROPERTY_NAME_AGE;
import static com.github.jinahya.assertj.validation.example.user.User.PROPERTY_NAME_NAME;
import static com.github.jinahya.assertj.validation.example.user.User.invalidAge;
import static com.github.jinahya.assertj.validation.example.user.User.invalidName;
import static com.github.jinahya.assertj.validation.example.user.User.userOf;
import static com.github.jinahya.assertj.validation.example.user.User.validAge;
import static com.github.jinahya.assertj.validation.example.user.User.validAgeForJunior;
import static com.github.jinahya.assertj.validation.example.user.User.validAgeForSenior;
import static com.github.jinahya.assertj.validation.example.user.User.validName;
import static org.assertj.core.api.Assertions.assertThat;

class User_ConstraintViolationAssert_Test {

    @Nested
    class NameTest {

        @Test
        void __ValidName() {
            final var name = validName();
            final var assertions = assertThatProperty(name);
            shouldPass(
                    () -> assertions.isValidFor(User.class, "name", s -> {
                        assertThat(s).isEmpty();
                    })
            );
        }

        @Test
        void __InvalidName() {
            final var name = invalidName();
            final var assertions = assertThatProperty(name);
            shouldFail(
                    () -> assertions.isValidFor(User.class, "name", s -> {
                        assertThat(s).hasSize(1).allSatisfy(cv -> {
                            assertThatConstraintViolation(cv)
                                    .hasInvalidValue(name)
                                    .hasRootBean(null)
                                    .hasRootBeanClass(User.class);
                            assertThatConstraintViolation(cv).extractingInvalidValue().isEqualTo(name);
                            assertThatConstraintViolation(cv).extractingRootBean().isNull();
                            assertThatConstraintViolation(cv).extractingRootBeanClass().isEqualTo(User.class);
                        });
                    })
            );
        }
    }

    @Nested
    class AgeTest {

        @Test
        void __ValidAge() {
            final var age = validAge();
            final var assertions = assertThatProperty(age);
            shouldPass(
                    () -> assertions.isValidFor(User.class, "age", s -> {
                        assertThat(s).isEmpty();
                    })
            );
        }

        @Test
        void __InvalidAge() {
            final var age = invalidAge();
            final var assertions = assertThatProperty(age);
            shouldFail(
                    () -> assertions.isValidFor(User.class, "age", s -> {
                        assertThat(s).hasSize(1).allSatisfy(cv -> {
                            assertThatConstraintViolation(cv)
                                    .hasInvalidValue(age)
                                    .hasRootBean(null)
                                    .hasRootBeanClass(User.class);
                            assertThatConstraintViolation(cv).extractingInvalidValue().isEqualTo(age);
                            assertThatConstraintViolation(cv).extractingRootBean().isNull();
                            assertThatConstraintViolation(cv).extractingRootBeanClass().isEqualTo(User.class);
                        });
                    })
            );
        }
    }

    @Test
    void __validName() {
        final var user = userOf(validName(), validAge());
        final var assertion = assertThatBean(user);
        {
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_NAME));
        }
        {
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
    }

    @Test
    void __invalidName() {
        final var user = userOf(invalidName(), validAge());
        final var assertion = assertThatBean(user);
        {
            shouldPass(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_NAME));
        }
        {
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
    }

    @Test
    void __validAgeForJunior() {
        final var user = userOf(validName(), validAgeForJunior());
        final var assertion = assertThatBean(user);
        {
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_NAME));
        }
        {
            assertion.targetingGroups(Junior.class);
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
        {
            assertion.targetingGroups(Senior.class);
            shouldPass(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
    }

    @Test
    void __validAgeForSenior() {
        final var user = userOf(validName(), validAgeForSenior());
        final var assertion = assertThatBean(user);
        {
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_NAME));
        }
        {
            assertion.targetingGroups(Junior.class);
            shouldPass(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
        {
            assertion.targetingGroups(Senior.class);
            shouldFail(() -> assertion.doesNotHaveValidProperty(PROPERTY_NAME_AGE));
        }
    }
}
