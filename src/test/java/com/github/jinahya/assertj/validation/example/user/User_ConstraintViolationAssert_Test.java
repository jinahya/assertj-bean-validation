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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintDescriptor;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintViolation;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldFail;
import static com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils.shouldPass;
import static com.github.jinahya.assertj.validation.example.user.User.invalidAge;
import static com.github.jinahya.assertj.validation.example.user.User.invalidName;
import static com.github.jinahya.assertj.validation.example.user.User.validAge;
import static com.github.jinahya.assertj.validation.example.user.User.validName;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
            final var violations = new HashSet<ConstraintViolation<User>>();
            shouldFail(
                    () -> assertions.isValidFor(User.class, "name", violations::addAll)
            );
            assertThat(violations).hasSize(1).allSatisfy(cv -> {
                assertThatConstraintViolation(cv)
                        .hasNoExecutableParameters()
                        .hasNoExecutableReturnValue()
                        .hasInvalidValue(name)
                        .hasNoLeafBean()
                        .hasNoRootBean()
                        .hasRootBeanClass(User.class);
                assertThatConstraintViolation(cv).extractingExecutableParameters().isNull();
                assertThatConstraintViolation(cv).extractingExecutableReturnValue().isNull();
                assertThatConstraintViolation(cv).extractingInvalidValue().isEqualTo(name);
                assertThatConstraintViolation(cv).extractingLeafBean2().isNull();
                assertThatConstraintViolation(cv).extractingRootBean().isNull();
                assertThatConstraintViolation(cv).extractingRootBeanClass().isEqualTo(User.class);
                {
                    assertThatConstraintViolation(cv)
                            .extractingConstraintDescriptor()
                            .extractingAnnotation()
                            .isInstanceOf(NotBlank.class);
                    assertThatConstraintDescriptor(cv.getConstraintDescriptor())
                            .extractingAnnotation()
                            .isInstanceOf(NotBlank.class);
                }
                {
                    assertThatConstraintViolation(cv)
                            .extractingPropertyPath()
                            .hasSize(1)
                            .element(0)
                            .hasName(User.PROPERTY_NAME_NAME);
                }
            });
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
            final var violations = new HashSet<ConstraintViolation<User>>();
            shouldFail(
                    () -> assertions.isValidFor(User.class, "age", violations::addAll)
            );
            assertThat(violations).hasSize(1).allSatisfy(cv -> {
                assertThatConstraintViolation(cv)
                        .hasNoExecutableParameters()
                        .hasNoExecutableReturnValue()
                        .hasInvalidValue(age)
                        .hasNoLeafBean()
                        .hasNoRootBean()
                        .hasRootBeanClass(User.class);
                assertThatConstraintViolation(cv).extractingExecutableParameters().isNull();
                assertThatConstraintViolation(cv).extractingExecutableReturnValue().isNull();
                assertThatConstraintViolation(cv).extractingInvalidValue().isEqualTo(age);
                assertThatConstraintViolation(cv).extractingLeafBean2().isNull();
                assertThatConstraintViolation(cv).extractingRootBean().isNull();
                assertThatConstraintViolation(cv).extractingRootBeanClass().isEqualTo(User.class);
                {
                    assertThatConstraintViolation(cv)
                            .extractingConstraintDescriptor()
                            .extractingAnnotation()
                            .isInstanceOfAny(PositiveOrZero.class, Min.class, Max.class);
                    assertThatConstraintDescriptor(cv.getConstraintDescriptor())
                            .extractingAnnotation()
                            .isInstanceOfAny(PositiveOrZero.class, Min.class, Max.class);
                }
                {
                    assertThatConstraintViolation(cv)
                            .extractingPropertyPath()
                            .hasSize(1)
                            .element(0)
                            .hasName(User.PROPERTY_NAME_AGE);
                }
            });
        }
    }
}
