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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

class UserAssert_HasValidPropertyAge_Test
        extends UserAssertTest {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @DisplayName("[Invalid] hasValidProperty(\"age\") should fail")
    @Test
    void hasValidPropertyAge_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstance(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[Invalid] hasValidProperty(\"name\") should pass")
    @Test
    void hasValidPropertyName_Pass_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstance(actual);
        Assertions.assertThatCode(() -> assertion.hasValidProperty("name"))
                .doesNotThrowAnyException();
    }

    @DisplayName("[Invalid] hasValidProperty(\"age\", Consumer) should fail")
    @Test
    void hasValidPropertyAgeConsumer_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstance(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("age", violations::add))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getAge());
                    Assertions.assertThat(cv.getPropertyPath())
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("age");
                            });
                });
    }

    @DisplayName("[Invalid] hasValidProperty(\"name\", Consumer) should pass")
    @Test
    void hasValidPropertyNameConsumer_Pass_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final UserAssert assertion = assertInstance(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatCode(() -> assertion.hasValidProperty("name", violations::add))
                .doesNotThrowAnyException();
        Assertions.assertThat(violations)
                .isEmpty();
    }
}
