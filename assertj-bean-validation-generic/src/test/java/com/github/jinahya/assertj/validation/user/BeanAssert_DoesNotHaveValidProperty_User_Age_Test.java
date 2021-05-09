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

import com.github.jinahya.assertj.validation.BeanAssert;
import com.github.jinahya.assertj.validation.BeanAssertArgumentConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_DoesNotHaveValidProperty_User_Age_Test {

    @DisplayName("assertBean(ofInvalidAge).doesNotHaveValidProperty(\"age\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidAge.class)
    void doesNotHaveValidProperty_Succeed_InvalidAge(
            @ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert a) {
        assertThatCode(() -> a.doesNotHaveValidProperty("age"))
                .doesNotThrowAnyException();
        a.doesNotHaveValidProperty("age", s -> {
            assertThat(s)
                    .isNotEmpty()
            ;
        });
    }

    @DisplayName("assertBean(valid).doesNotHaveValidProperty(\"age\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfValid.class)
    void doesNotHaveValidProperty_Fail_Valid(
            @ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert a) {
        assertThatThrownBy(() -> a.doesNotHaveValidProperty("age"))
                .isInstanceOf(AssertionError.class);
    }
}
