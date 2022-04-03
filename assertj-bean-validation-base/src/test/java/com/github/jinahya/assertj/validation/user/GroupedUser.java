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
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static java.util.concurrent.ThreadLocalRandom.current;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
class GroupedUser {

    static GroupedUser newValidInstance() {
        final GroupedUser instance = new GroupedUser();
        instance.setName(Long.toString(System.nanoTime()));
        instance.setAge(current().nextInt() & Integer.MAX_VALUE);
        return instance;
    }

    static GroupedUser setInvalidName(final GroupedUser instance) {
        instance.setName(current().nextBoolean() ? "" : current().nextBoolean() ? " " : null);
        return instance;
    }

    static GroupedUser setInvalidAge(final GroupedUser instance) {
        instance.setAge(current().nextInt() | Integer.MIN_VALUE);
        return instance;
    }

    static GroupedUser newInvalidInstance() {
        final GroupedUser instance = newValidInstance();
        if (current().nextBoolean()) {
            setInvalidName(instance);
        } else {
            setInvalidAge(instance);
        }
        return instance;
    }

    static GroupedUser newInstanceWithInvalidName() {
        return setInvalidName(newValidInstance());
    }

    static GroupedUser newInstanceWithInvalidAge() {
        return setInvalidAge(newValidInstance());
    }

    public GroupedUser() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "name=" + name
               + ",age=" + age
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final GroupedUser that = (GroupedUser) obj;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    //    @jakarta.validation.constraints.NotBlank(groups = {Default.class, NameOnly.class})
//    @javax.validation.constraints.NotBlank(groups = {Default.class, NameOnly.class})
    private String name;

    //    @jakarta.validation.constraints.PositiveOrZero(groups = {Default.class, AgeOnly.class})
//    @javax.validation.constraints.PositiveOrZero(groups = {Default.class, AgeOnly.class})
    private int age;
}
