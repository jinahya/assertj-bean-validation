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

import com.github.jinahya.assertj.validation.AbstractBeanAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Slf4j
class UserAssert_IsValid_Test
        extends UserAssertTest {

    @DisplayName("[Valid] isValid() should pass")
    @Test
    void isValid_Pass_Valid() {
        final User actual = User.newValidInstance();
        final UserAssert assertion = assertInstant(actual);
        assertion.isValid();
    }

    @DisplayName("[InvalidName] isValid() should fail")
    @Test
    void isValid_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        Assertions.assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidName] isValid(Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidName() {
        final User actual = User.newInstanceWithInvalidName();
        final UserAssert assertion = assertInstant(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValid(violations::add))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getName());
                    Assertions.assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("name");
                            });
                });
    }

    @DisplayName("[InvalidAge] isValid() should fail")
    @Test
    void isValid_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        Assertions.assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[InvalidAge] isValid(Consumer) should fail")
    @Test
    void isValidWithConsumer_Fail_InvalidAge() {
        final User actual = User.newInstanceWithInvalidAge();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValid(violations::add))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getInvalidValue())
                            .isEqualTo(actual.getAge());
                    Assertions.assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName())
                                        .isEqualTo("age");
                            });
                });
    }
}
