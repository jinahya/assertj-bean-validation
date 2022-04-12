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

class IsValidFor_Name_Test {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @DisplayName("[Valid] isValidFor(\"name\") should pass")
    @Test
    void isValidForName_Pass_Valid() {
        final var actual = User.newValidName();
        final var assertion = ValidationAssertions.assertThatProperty(actual);
        assertion.isValidFor(User.class, "name");
    }

    @DisplayName("[Valid] isValidFor(\"name\", Consumer) should pass")
    @Test
    void isValidForNameConsumer_Pass_Valid() {
        final var actual = User.newValidName();
        final var assertion = ValidationAssertions.assertThatProperty(actual);
        final var violations = new HashSet<ConstraintViolation<User>>();
        assertion.isValidFor(User.class, "name", violations::add);
        assertThat(violations)
                .isEmpty();
    }

    @DisplayName("[Invalid] isValidFor(User.class, \"name\") should fail")
    @Test
    void isValidForName_Fail_InvalidName() {
        final var actual = User.newInvalidName();
        final var assertion = ValidationAssertions.assertThatProperty(actual);
        assertThatThrownBy(() -> assertion.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("[Invalid] isValidFor(User.class, \"name\", Consumer) should fail")
    @Test
    void isValidForNameConsumer_Fail_InvalidName() {
        final var actual = User.newInvalidName();
        final var assertion = ValidationAssertions.assertThatProperty(actual);
        final var violations = new HashSet<ConstraintViolation<User>>();
        assertThatThrownBy(() -> assertion.isValidFor(User.class, "name", violations::add))
                .isInstanceOf(AssertionError.class);
        assertThat(violations)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(cv -> {
                    assertThat(cv.getInvalidValue()).isEqualTo(actual);
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                assertThat(n.getName()).isEqualTo("name");
                            });
                });
    }
}
