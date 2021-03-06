package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-jakarta
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

import com.github.jinahya.assertj.validation.jakarta.ValueAssert;
import com.github.jinahya.assertj.validation.jakarta.ValueAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsValidFor_User_Test {

    @DisplayName("assertValue(validAge).isValidFor(User.class, \"age\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidAge.class)
    void isValidFor_Succeed_ValidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(validName).isValidFor(User.class, \"name\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidName.class)
    void isValidFor_Succeed_ValidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidAge).isValidFor(User.class, \"age\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidAge.class)
    void isValidFor_Fail_InvalidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(invalidName).isValidFor(User.class, \"name\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidName.class)
    void isValidFor_Fail_InvalidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }
}
