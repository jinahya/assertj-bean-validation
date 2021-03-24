package com.github.jinahya.assertj.validation;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link BeanValidationAssertions#assertThat(BeanWrapper)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class BeanValidationAssertions_AssertThat_BeanWrapper_Test extends BeanValidationAssertionsTest {

    @DisplayName("assertThat(null) throws NullPointerException")
    @Test
    void assertThat_NullPointerException_WrapperIsNull() {
        assertThatThrownBy(() -> BeanValidationAssertions.assertThat(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("assertThat(wrapper) does not throw any exception")
    @Test
    void assertBeanType__() {
        assertThatCode(() -> BeanValidationAssertions.assertThat(bean(new Object())))
                .doesNotThrowAnyException();
    }
}
