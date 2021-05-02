package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import com.github.jinahya.assertj.validation.javax.BeanAssert;
import com.github.jinahya.assertj.validation.javax.BeanAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanAssert_IsValid_User_Test {

    @DisplayName("assertBean(valid).isValid() succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfValid.class)
    void isValid_Succeed_Valid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        assertThatCode(a::isValid)
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBean(invalid).isValid() fails")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isValid_Fail_Invalid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
    }
}
