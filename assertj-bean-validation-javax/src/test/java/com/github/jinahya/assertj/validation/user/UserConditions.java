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

import org.assertj.core.api.Condition;

public final class UserConditions {

    public static final Condition<User> NAMED_JANE
            = new Condition<>(v -> "Jane".equalsIgnoreCase(v.getName()), "named Jane");

    public static final Condition<User> NAMED_JOHN
            = new Condition<>(v -> "John".equalsIgnoreCase(v.getName()), "named John");

    private UserConditions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
