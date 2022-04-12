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

import com.github.jinahya.assertj.validation.BeanAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SeniorRegistration_IsValid_Test {

    @Test
    void isValid_ShouldFail_59YearsOld() {
        final var age = 59;
        assertThatCode(() -> ValidationAssertions.assertThatProperty(age).isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
        assertThatCode(() -> ValidationAssertions.assertThatProperty(age).isValidFor(Registration.class, "user.age"))
                .doesNotThrowAnyException();
        final var user = User.of("John", age);
        final var actual = SeniorRegistration.of(user);
        final BeanAssert<?, SeniorRegistration> assertion = ValidationAssertions.assertThatBean(actual);
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }
}
