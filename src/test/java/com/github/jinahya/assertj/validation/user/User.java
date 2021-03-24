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

import com.github.jinahya.assertj.validation.BeanValidationTestUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import static java.util.concurrent.ThreadLocalRandom.current;

@Data
class User {

    static User newValidInstance() {
        final User instance = new User();
        instance.setName(Long.toString(System.nanoTime()));
        instance.setAge(current().nextInt() & Integer.MAX_VALUE);
        return BeanValidationTestUtils.requireValid(instance);
    }

    private static User setInvalidName(final User instance) {
        instance.setName(current().nextBoolean() ? "" : current().nextBoolean() ? " " : null);
        return instance;
    }

    private static User setInvalidAge(final User instance) {
        instance.setAge(current().nextInt() | Integer.MIN_VALUE);
        return instance;
    }

    static User newInvalidInstance() {
        final User instance = newValidInstance();
        do {
            if (current().nextBoolean()) {
                setInvalidName(instance);
            }
            if (current().nextBoolean()) {
                setInvalidAge(instance);
            }
        } while (BeanValidationTestUtils.isValid(instance));
        return instance;
    }

    static User newInstanceWithInvalidName() {
        return setInvalidName(newValidInstance());
    }

    static User newInstanceWithInvalidAge() {
        return setInvalidAge(newValidInstance());
    }

    @NotBlank
    private String name;

    @PositiveOrZero
    private int age;
}
