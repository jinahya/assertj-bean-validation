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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrExceptionInInitializerError;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"java:S125"})
final class GroupDefinitionExceptionUtils {

    static final Class<?> GROUP_DEFINITION_EXCEPTION_CLASS
            = getClassForSuffixOrExceptionInInitializerError("GroupDefinitionException");

    static boolean isGroupDefinitionException(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return GROUP_DEFINITION_EXCEPTION_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireGroupDefinitionException(final T object, final boolean nullable) {
        if (!isGroupDefinitionException(object, nullable)) {
            throw new IllegalArgumentException(
                    "not an instance of " + GROUP_DEFINITION_EXCEPTION_CLASS + ": " + object);
        }
        return object;
    }

    private GroupDefinitionExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
