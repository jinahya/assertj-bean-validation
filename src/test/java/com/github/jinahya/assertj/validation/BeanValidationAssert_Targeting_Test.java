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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BeanValidationAssert_Targeting_Test {

    @Test
    void targeting_DoesNotThrow_GroupsIsNull() {
        final BeanValidationAssert a = new BeanValidationAssert(new Object());
        assertThatCode(() -> a.targeting((Class<?>[]) null))
                .doesNotThrowAnyException();
        assertThat(a.groups()).isNotNull();
    }

    @Test
    void groups_DoesNotThrow_GroupsIsNull() {
        final BeanValidationAssert a = new BeanValidationAssert(new Object());
        assertThatCode(() -> a.groups((Class<?>[]) null))
                .doesNotThrowAnyException();
        assertThat(a.groups()).isNotNull();
    }
}
