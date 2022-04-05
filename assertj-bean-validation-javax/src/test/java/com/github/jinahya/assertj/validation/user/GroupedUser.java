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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.Default;

@Getter
@EqualsAndHashCode
@ToString
@SuperBuilder(toBuilder = true)
class GroupedUser {

    static GroupedUser newValidInstance() {
        final User user = User.newValidInstance();
        return builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    static GroupedUser newInstanceWithInvalidName() {
        final User user = User.newInstanceWithInvalidName();
        return builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    static GroupedUser newInstanceWithInvalidAge() {
        final User user = User.newInstanceWithInvalidAge();
        return builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    @NotBlank(groups = {Default.class, NameOnly.class})
    private String name;

    @PositiveOrZero(groups = {Default.class, AgeOnly.class})
    private int age;
}
