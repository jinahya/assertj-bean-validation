package com.github.jinahya.assertj.validation.example.user;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class User {

    static final String PROPERTY_NAME_NAME = "name";

    static final String PROPERTY_NAME_AGE = "age";

    static final int MAX_AGE = 127;

    static String validName() {
        return "name";
    }

    static String invalidName() {
        return ThreadLocalRandom.current().nextBoolean()
                ? null
                : (ThreadLocalRandom.current().nextBoolean() ? "" : "   ");
    }

    static int validAge() {
        return ThreadLocalRandom.current().nextInt(MAX_AGE + 1);
    }

    static int invalidAge() {
        return (ThreadLocalRandom.current().nextBoolean()
                ? (ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE) // not has been born yet
                : ThreadLocalRandom.current().nextInt(MAX_AGE + 1, Integer.MAX_VALUE)) // too old
                ;
    }

    static User newInstance(final boolean validName, final boolean validAge) {
        final var name = validName ? validName() : invalidName();
        final var age = validAge ? validAge() : invalidAge();
        return new User(name, age);
    }

    @NotBlank
    private String name;

    @Max(MAX_AGE)
    @PositiveOrZero
    private int age;
}
