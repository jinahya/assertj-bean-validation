package com.github.jinahya.assertj.user;

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

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@EqualsAndHashCode
@ToString
@SuperBuilder
public class User {

    static String newValidName() {
        return Long.toString(System.nanoTime());
    }

    static int newValidAge() {
        return ThreadLocalRandom.current().nextInt(MIN_VALUE_AGE, MAX_VALUE_AGE);
    }

    static User.UserBuilder<?, ?> newValidInstance_() {
        return User.builder()
                .name(newValidName())
                .age(newValidAge());
    }

    public static User newValidInstance() {
        return newValidInstance_().build();
    }

    static String newInvalidName() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return null;
        }
        return "    ";
    }

    static int newInvalidAge() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE;
        }
        return ThreadLocalRandom.current().nextInt(MAX_VALUE_AGE + 1, Integer.MAX_VALUE);
    }

    static User.UserBuilder<?, ?> newInstanceWithInvalidName_() {
        return newValidInstance_()
                .name(newInvalidName())
                ;
    }

    public static User newInstanceWithInvalidName() {
        final User instance = newInstanceWithInvalidName_().build();
        final String name = instance.name;
        return instance;
    }

    static User.UserBuilder<?, ?> newInstanceWithInvalidAge_() {
        return newValidInstance_()
                .age(newInvalidAge())
                ;
    }

    public static User newInstanceWithInvalidAge() {
        final User instance = newInstanceWithInvalidAge_().build();
        final int age = instance.age;
        return instance;
    }

    public static final int MIN_VALUE_AGE = 0;

    public static final int MAX_VALUE_AGE = 128;

    @NotBlank
    private final String name;

    @Max(MAX_VALUE_AGE)
    @Min(MIN_VALUE_AGE)
    @PositiveOrZero
    private final int age;
}
