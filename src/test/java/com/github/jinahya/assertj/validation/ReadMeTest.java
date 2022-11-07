package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
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

import com.github.jinahya.assertj.validation.example.user.Registration;
import com.github.jinahya.assertj.validation.example.user.SeniorRegistration;
import com.github.jinahya.assertj.validation.example.user.User;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ReadMeTest {

    @Test
    void test1() {
        assertBean(new User("Jane", 28))  // valid
                .isValid()                // should pass
                .hasValidProperty("name") // should pass
                .hasValidProperty("age"); // should pass
    }

    @Test
    void test2() {
        assertProperty("John").isValidFor(User.class, "name");
        assertThatThrownBy(() -> assertProperty(null).isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertProperty("").isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertProperty(" ").isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
        assertProperty(31).isValidFor(User.class, "age");
        assertThatThrownBy(() -> assertProperty(-1).isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertProperty(297).isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void test3() {
        assertBean(new User("Jane", 21))
                .isValidFor(Registration.class, "user");
        assertThatThrownBy(() -> assertBean(null).isValidFor(Registration.class, "user"))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertBean(new User("John", 59)).isValidFor(SeniorRegistration.class, "user"))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void test4() {
        User user = new User("John", 300);
        assertThatThrownBy(() -> assertBean(user).isValid())
                .isInstanceOf(AssertionError.class);
        assertThatCode(() -> assertBean(user).isValidFor(Registration.class, "user"))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> assertBean(null).isValidFor(Registration.class, "user"))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertBean(new SeniorRegistration(user)).isValid())
                .isInstanceOf(AssertionError.class);
    }
}
