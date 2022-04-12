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

import com.github.jinahya.assertj.validation.BeanAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserAssert_IsValid_Test
        extends UserAssertTest {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @DisplayName("[Valid] isValid() should pass")
    @Test
    void isValid_Pass_Valid() {
        final var actual = User.newValidInstance();
        final var assertion = assertInstance(actual);
        assertion.isValid();
    }

    @DisplayName("[InvalidName] isValid() should fail")
    @Test
    void isValid_Fail_InvalidName() {
        final var actual = User.newInstanceWithInvalidName();
        final var assertion = assertInstance(actual);
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidName] isValid(Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidName() {
        final var actual = User.newInstanceWithInvalidName();
        final var assertion = assertInstance(actual);
        final var violations = new HashSet<ConstraintViolation<User>>();
        assertThatThrownBy(() -> assertion.isValid(violations::add))
                .isInstanceOf(AssertionError.class);
        assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getName());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                assertThat(n.getName())
                                        .isEqualTo("name");
                            });
                });
    }

    @DisplayName("[InvalidAge] isValid() should fail")
    @Test
    void isValid_Fail_InvalidAge() {
        final var actual = User.newInstanceWithInvalidAge();
        final BeanAssert<?, User> assertion = ValidationAssertions.assertThatBean(actual);
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidAge] isValid(Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidAge() {
        final var actual = User.newInstanceWithInvalidAge();
        final var assertion = ValidationAssertions.assertThatBean(actual);
        final var violations = new HashSet<ConstraintViolation<User>>();
        assertThatThrownBy(() -> assertion.isValid(violations::add))
                .isInstanceOf(AssertionError.class);
        assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getAge());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                assertThat(n.getName())
                                        .isEqualTo("age");
                            });
                });
    }
}
