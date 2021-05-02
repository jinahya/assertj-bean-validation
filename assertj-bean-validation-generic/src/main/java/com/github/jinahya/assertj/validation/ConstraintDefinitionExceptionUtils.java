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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffix;
import static java.util.Objects.requireNonNull;

final class ConstraintDefinitionExceptionUtils {

    private static final Class<?> CONSTRAINT_DEFINITION_EXCEPTION_CLASS
            = getClassForSuffix("ConstraintDefinitionException");

    static boolean isInstanceOfConstraintDefinitionExceptionClass(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return CONSTRAINT_DEFINITION_EXCEPTION_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireInstanceOfConstraintDefinitionExceptionClass(final T object, final boolean nullable) {
        if (!isInstanceOfConstraintDefinitionExceptionClass(object, nullable)) {
            throw new IllegalArgumentException(
                    "not an instance of " + CONSTRAINT_DEFINITION_EXCEPTION_CLASS + ": " + object);
        }
        return object;
    }

    private ConstraintDefinitionExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
