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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static java.util.concurrent.ThreadLocalRandom.current;

@Setter(AccessLevel.PACKAGE)
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE, toBuilder = true)
public class User {

    public static User newValidInstance() {
        final String name = Long.toString(System.nanoTime());
        final int age = current().nextInt() & Integer.MAX_VALUE;
        return builder()
                .name(name)
                .age(age)
                .valid(true)
                .build();
    }

    private static User setInvalidName(final User instance) {
        final String invalidName = current().nextBoolean() ? "" : current().nextBoolean() ? " " : null;
        return instance.toBuilder()
                .name(invalidName)
                .valid(false)
                .build();
    }

    private static User setInvalidAge(final User instance) {
        final int invalidAge = current().nextInt() | Integer.MIN_VALUE;
        return instance.toBuilder()
                .age(invalidAge)
                .valid(false)
                .build();
    }

    public static User newInstanceWithInvalidName() {
        return setInvalidName(newValidInstance());
    }

    public static User newInstanceWithInvalidAge() {
        return setInvalidAge(newValidInstance());
    }

    public static User newInvalidInstance() {
        if (current().nextBoolean()) {
            return newInstanceWithInvalidName();
        } else {
            return newInstanceWithInvalidAge();
        }
    }

    private User() {
        throw new NonInstantiatableAssertionError();
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "name=" + name
               + ",age=" + age
               + ",valid=" + valid
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final User that = (User) obj;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @jakarta.validation.constraints.NotBlank
    @javax.validation.constraints.NotBlank
    private String name;

    @jakarta.validation.constraints.PositiveOrZero
    @javax.validation.constraints.PositiveOrZero
    private int age;

    @EqualsAndHashCode.Exclude
    private boolean valid;
}
