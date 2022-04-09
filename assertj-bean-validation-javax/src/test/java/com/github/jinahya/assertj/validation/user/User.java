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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
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
        return new User(newValidName(), newValidAge());
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

    public static User newInstanceWithInvalidName() {
        final User instance = newValidInstance();
        instance.setName(newInvalidName());
        return instance;
    }

    public static User newInstanceWithInvalidAge() {
        final User instance = newValidInstance();
        instance.setAge(newInvalidAge());
        return instance;
    }

    public static final int MIN_VALUE_AGE = 0;

    public static final int MAX_VALUE_AGE = 128;

    public User(final String name, final int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotBlank
    String name;

    @Max(MAX_VALUE_AGE)
    @Min(MIN_VALUE_AGE)
    @PositiveOrZero int age;
}
