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

import java.lang.reflect.Method;
import java.util.Set;

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffix;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"java:S119", "java:S125"})
final class ConstraintViolationExceptionUtils {

    private static final Class<?> CONSTRAINT_VIOLATION_EXCEPTION_CLASS
            = getClassForSuffix("ConstraintViolationException");

    private static boolean isInstanceOfConstraintViolationExceptionClass(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return CONSTRAINT_VIOLATION_EXCEPTION_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireInstanceOfConstraintViolationExceptionClass(final T object, final boolean nullable) {
        if (!isInstanceOfConstraintViolationExceptionClass(object, nullable)) {
            throw new IllegalArgumentException(
                    "not an instance of " + CONSTRAINT_VIOLATION_EXCEPTION_CLASS + ": " + object);
        }
        return object;
    }

    // --------------------------------------------------------------------------------------- getConstraintViolations()
    private static final Method GET_CONSTRAINT_VIOLATIONS_METHOD;

    static {
        try {
            GET_CONSTRAINT_VIOLATIONS_METHOD
                    = CONSTRAINT_VIOLATION_EXCEPTION_CLASS.getMethod("getConstraintViolations");
        } catch (final NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> getConstraintViolations(final Object actual) {
        try {
            return (Set<CONSTRAINT_VIOLATION>) GET_CONSTRAINT_VIOLATIONS_METHOD.invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
