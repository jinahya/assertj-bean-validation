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

    // partial
    protected static class UserBuilder {

    }

    // partial
    protected static UserBuilder builder() {
        return new UserBuilder();
    }

    public static int newValidAge() {
        return current().nextInt() & Integer.MAX_VALUE;
    }

    public static String newValidName() {
        return Long.toString(System.nanoTime());
    }

    public static User newValidInstance() {
        final String name = Long.toString(System.nanoTime());
        final int age = current().nextInt() & Integer.MAX_VALUE;
        return builder()
                .age(newValidAge())
                .name(newValidName())
                .valid(true)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static int newInvalidAge() {
        return current().nextInt() | Integer.MIN_VALUE;
    }

    private static UserBuilder withInvalidAge(final UserBuilder builder) {
        return builder
                .age(newInvalidAge())
                .valid(false);
    }

    private static User withInvalidAge(final User instance) {
        return withInvalidAge(instance.toBuilder()).build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static String newInvalidName() {
        return current().nextBoolean() ? "" : current().nextBoolean() ? " " : null;
    }

    private static UserBuilder withInvalidName(final UserBuilder builder) {
        return builder
                .name(newInvalidName())
                .valid(false);
    }

    private static User withInvalidName(final User instance) {
        return withInvalidName(instance.toBuilder()).build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static User newInstanceWithInvalidName() {
        return withInvalidName(newValidInstance());
    }

    public static User newInstanceWithInvalidAge() {
        return withInvalidAge(newValidInstance());
    }

    public static User newInvalidInstance() {
        final UserBuilder builder = newValidInstance().toBuilder();
        switch (current().nextInt(3)) {
            case 0:
                withInvalidAge(builder);
                break;
            case 1:
                withInvalidName(builder);
                break;
            default:
                withInvalidAge(builder);
                withInvalidName(builder);
                break;
        }
        return builder.build();
    }

    // -----------------------------------------------------------------------------------------------------------------
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
