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

import com.github.jinahya.assertj.validation.AbstractPropertyAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Slf4j
class ValidationAssertions_IsValidForAge_Test {

    @DisplayName("[Valid] isValidFor(User.class, \"age\") should pass")
    @Test
    void isValidForAge_Pass_Valid() {
        final int actual = User.newValidAge();
        final AbstractPropertyAssert<?, Integer> assertion = ValidationAssertions.assertProperty(actual);
        assertion.isValidFor(User.class, "age");
    }

    @DisplayName("[Invalid] isValidFor(User.class, \"age\") should fail")
    @Test
    void isValidForAge_Fail_InvalidAge() {
        final int actual = User.newInvalidAge();
        final AbstractPropertyAssert<?, Integer> assertion = ValidationAssertions.assertProperty(actual);
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[Invalid] isValidFor(User.class, \"age\", Consumer) should fail")
    @Test
    void isValidForAgeConsumer_Fail_InvalidAge() {
        final int actual = User.newInvalidAge();
        final AbstractPropertyAssert<?, Integer> assertion = ValidationAssertions.assertProperty(actual);
        final Set<ConstraintViolation<User>> violations = new HashSet<>();
        Assertions.assertThatThrownBy(() -> assertion.isValidFor(User.class, "age", cv -> {
                    Assertions.assertThat(cv).isNotNull();
                    violations.add(cv);
                }))
                .isInstanceOf(AssertionError.class);
        Assertions.assertThat(violations)
                .isNotEmpty()
                .allSatisfy(cv -> {
                    Assertions.assertThat(cv.getInvalidValue()).isEqualTo(actual);
                    Assertions.assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                Assertions.assertThat(n.getName()).isEqualTo("age");
                            });
                });
    }
}
