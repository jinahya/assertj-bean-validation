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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

class GroupedUser {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static GroupedUser newValidInstance() {
        final User user = User.newValidInstance();
        return new GroupedUser(user.getName(), user.getAge());
    }

    static GroupedUser newInstanceWithInvalidName() {
        return of(User.newInstanceWithInvalidName());
    }

    static GroupedUser newInstanceWithInvalidAge() {
        return of(User.newInstanceWithInvalidAge());
    }

    static GroupedUser of(final User user) {
        Objects.requireNonNull(user, "user is null");
        return new GroupedUser(user.getName(), user.getAge());
    }

    GroupedUser(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @NotBlank(groups = {Default.class, NameOnly.class})
    String name;

    @PositiveOrZero(groups = {Default.class, AgeOnly.class})
    int age;
}
