package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-generic
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

import com.github.jinahya.assertj.validation.ValueAssert;
import com.github.jinahya.assertj.validation.ValueAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsValidFor_User_Age_Test {

    @DisplayName("assertValue(validAge).isValidFor(User.class, \"age\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidAge.class)
    void isValidFor_Succeed_ValidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidAge).isValidFor(User.class, \"age\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidAge.class)
    void isValidFor_Fail_InvalidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }
}
