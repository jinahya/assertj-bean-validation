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

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ThreadLocalRandom;

public class User {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static String newValidName() {
        return Long.toString(System.nanoTime());
    }

    static int newValidAge() {
        return ThreadLocalRandom.current().nextInt(MIN_VALUE_AGE, MAX_VALUE_AGE);
    }

    static User newValidInstance() {
        return User.of(newValidName(), newValidAge());
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

    static User newInstanceWithInvalidName() {
        final User instance = newValidInstance();
        instance.setName(newInvalidName());
        return instance;
    }

    static User newInstanceWithInvalidAge() {
        final User instance = newValidInstance();
        instance.setAge(newInvalidAge());
        return instance;
    }

    static final int MIN_VALUE_AGE = 0;

    static final int MAX_VALUE_AGE = 128;

    static User of(final String name, final int age) {
        final User instance = new User();
        instance.name = name;
        instance.age = age;
        return instance;
    }

    User() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "name=" + name
               + ",age=" + age
               + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns current value of {@code age} property.
     *
     * @return current value of the {@code age} property.
     */
    public int getAge() {
        return age;
    }

    /**
     * Replaces current value of {@code age} property with specified value.
     *
     * @param age new value for the {@code age} property.
     */
    public void setAge(final int age) {
        this.age = age;
    }

    @NotBlank
    private String name;

    @Max(MAX_VALUE_AGE)
    @Min(MIN_VALUE_AGE)
    @PositiveOrZero
    private int age;
}
