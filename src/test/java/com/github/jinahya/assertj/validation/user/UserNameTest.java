package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 Jinahya, Inc.
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

import com.github.jinahya.assertj.validation.BeanPropertyValidationAssert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertBeanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class UserNameTest {

    private static Stream<String> invalidNames() {
        return UserTest.invalidNames();
    }

    private static Stream<String> validNames() {
        return UserTest.validNames();
    }

    @MethodSource({"invalidNames"})
    @ParameterizedTest
    void isValidFor_Fail_InvalidName(final String name) {
        final BeanPropertyValidationAssert a = assertBeanProperty(name);
        assertThatThrownBy(() -> a.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"validNames"})
    @ParameterizedTest
    void isValidFor_Succeed_ValidName(final String name) {
        final BeanPropertyValidationAssert a = assertBeanProperty(name);
        assertThatCode(() -> a.isValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }

    // --------------------------------------------------------------------------------------------------- isNotValidFor
    @MethodSource({"invalidNames"})
    @ParameterizedTest
    void isNotValidFor_Succeed_InvalidName(final String name) {
        final BeanPropertyValidationAssert a = assertBeanProperty(name);
        assertThatCode(
                () -> a.isNotValidFor(User.class, "name", s -> {
                }))
                .doesNotThrowAnyException();
    }

    @MethodSource({"validNames"})
    @ParameterizedTest
    void isNotValidFor_Fail_ValidName(final String name) {
        final BeanPropertyValidationAssert a = assertBeanProperty(name);
        assertThatThrownBy(
                () -> a.isNotValidFor(User.class, "name", s -> {
                }))
                .isInstanceOf(AssertionError.class);
    }
}
