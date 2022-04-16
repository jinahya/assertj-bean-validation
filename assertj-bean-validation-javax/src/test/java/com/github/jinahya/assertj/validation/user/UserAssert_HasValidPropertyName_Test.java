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

class UserAssert_HasValidPropertyName_Test
        extends UserAssertTest {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @DisplayName("[Valid] hasValidProperty(both) should pass")
    @Test
    void hasValidProperty_Pass_Valid() {
        final User actual = User.newValidInstance();
        final UserAssert assertion = assertInstance(actual);
        assertion
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("[InvalidName] hasValidProperty(\"name\") should fail")
    @Test
    void hasValidPropertyName_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstance(actual);
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidName] hasValidProperty(\"age\") should pass")
    @Test
    void hasValidPropertyAge_Pass_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstance(actual);
        Assertions.assertThatCode(() -> assertion.hasValidProperty("age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("[InvalidName] hasValidProperty(\"name\", Consumer) should fail")
    @Test
    void hasValidPropertyNameConsumer_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstance(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.hasValidProperty("name", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getPropertyPath())
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("name");
                            });
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getName());
                });
    }

    @DisplayName("[InvalidName] hasValidProperty(\"age\", Consumer) should pass")
    @Test
    void hasValidPropertyAgeConsumer_Pass_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstance(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatCode(() -> assertion.hasValidProperty("age", cv -> {
                    Assertions.assertThat(cv)
                            .isNotNull();
                    violations.add(cv);
                }))
                .doesNotThrowAnyException();
        Assertions.assertThat(violations)
                .isEmpty();
    }
}
