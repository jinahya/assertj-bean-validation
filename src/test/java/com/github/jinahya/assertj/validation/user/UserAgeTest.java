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

import java.util.stream.IntStream;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertBeanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class UserAgeTest {

    private static IntStream invalidAges() {
        return UserTest.invalidAges();
    }

    private static IntStream validAges() {
        return UserTest.validAges();
    }

    @MethodSource({"invalidAges"})
    @ParameterizedTest
    void isValidFor_Fail_InvalidAge(final int age) {
        final BeanPropertyValidationAssert a = assertBeanProperty(age);
        assertThatThrownBy(() -> a.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"validAges"})
    @ParameterizedTest
    void isValidFor_Succeed_ValidAge(final int age) {
        final BeanPropertyValidationAssert a = assertBeanProperty(age);
        assertThatCode(() -> a.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }
}
