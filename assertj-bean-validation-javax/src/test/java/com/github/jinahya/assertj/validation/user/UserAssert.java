package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import com.github.jinahya.assertj.validation.AbstractBeanAssert;
import org.assertj.core.api.Assertions;

class UserAssert
        extends AbstractBeanAssert<UserAssert, User> {

    UserAssert(final User actual) {
        super(actual, UserAssert.class);
    }

    public UserAssert isNamedJane() {
        return is(UserConditions.NAMED_JANE);
    }

    public UserAssert isNamedJohn() {
        return is(UserConditions.NAMED_JOHN);
    }

    public UserAssert hasAge(final int expected) {
        return isNotNull()
                .satisfies(a -> Assertions.assertThat(a.getAge()).isEqualTo(expected));
    }
}
