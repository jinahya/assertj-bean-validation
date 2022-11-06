package com.github.jinahya.assertj.validation.example.user2;

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

import com.github.jinahya.assertj.validation.example.user.Junior;
import com.github.jinahya.assertj.validation.example.user.Senior;
import com.github.jinahya.assertj.validation.example.user.User;
import com.github.jinahya.assertj.validation.example.user.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class User2 {

    static User2 from(final User user) {
        return new User2(user.getName(), user.getAge());
    }

    static User2 newInstance(final boolean validName, final boolean validAge) {
        return from(User.newInstance(validName, validAge));
    }

    @NotBlank
    private String name;

    @Max(User.MAX_AGE)
    @Min(value = UserConstants.MIN_AGE_FOR_SENIOR_INCLUSIVE, groups = {Senior.class})
    @Max(value = UserConstants.MAX_AGE_FOR_JUNIOR_EXCLUSIVE - 1, groups = {Junior.class})
    @PositiveOrZero
    private int age;
}
