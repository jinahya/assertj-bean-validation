package com.github.jinahya.assertj.validation.user.group;

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

import javax.validation.groups.Default;

import static java.util.concurrent.ThreadLocalRandom.current;

@Data
class User {

    static User newValidInstance() {
        final User instance = new User();
        instance.setName(Long.toString(System.nanoTime()));
        instance.setAge(current().nextInt() & Integer.MAX_VALUE);
        return BeanValidationTestUtils.requireValid(instance);
    }

    static User setInvalidName(final User instance) {
        instance.setName(current().nextBoolean() ? "" : current().nextBoolean() ? " " : null);
        return instance;
    }

    static User setInvalidAge(final User instance) {
        instance.setAge(current().nextInt() | Integer.MIN_VALUE);
        return instance;
    }

    static User newInvalidInstance() {
        final User instance = newValidInstance();
        if (current().nextBoolean()) {
            setInvalidName(instance);
        } else {
            setInvalidAge(instance);
        }
        return instance;
    }

    static User newInstanceWithInvalidName() {
        return setInvalidName(newValidInstance());
    }

    static User newInstanceWithInvalidAge() {
        return setInvalidAge(newValidInstance());
    }

    @javax.validation.constraints.NotBlank(groups = {Default.class, NameOnly.class})
    @jakarta.validation.constraints.NotBlank(groups = {Default.class, NameOnly.class})
    private String name;

    @javax.validation.constraints.PositiveOrZero(groups = {Default.class, AgeOnly.class})
    @jakarta.validation.constraints.PositiveOrZero(groups = {Default.class, AgeOnly.class})
    private int age;
}
