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

import java.util.Set;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@SuppressWarnings({"java:S125"})
final class ConstraintViolationExceptionUtils {

    private static final String SUFFIX = "ConstraintViolationException";

    private static Class<?> constraintViolationClass;

    private static Class<?> constraintViolationClass() {
        Class<?> c = constraintViolationClass;
        if (c == null) {
            constraintViolationClass = c = ReflectionUtils.getClassForSuffix("ConstraintViolationException");
        }
        return c;
    }

    static <R> R applyConstraintViolationExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    /**
     * Returns the class of {@code ....validation.Path.PropertyNode}.
     *
     * @return the class of {@code ....validation.Path.PropertyNode}.
     */
    static Class<?> getConstraintViolationExceptionClass() {
        return applyConstraintViolationExceptionClass(Function.identity());
    }

    static boolean isInstanceOfConstraintViolationExceptionClass(final Object object) {
        return constraintViolationClass().isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireInstanceOfConstraintViolationExceptionClass(final T object) {
        if (!isInstanceOfConstraintViolationExceptionClass(object)) {
            throw new IllegalArgumentException("not an instance of " + constraintViolationClass() + ": " + object);
        }
        return object;
    }

    static <T> T requireNullOrInstanceOfConstraintViolationExceptionClass(final T object) {
        if (object == null) {
            return null;
        }
        return requireInstanceOfConstraintViolationExceptionClass(object);
    }

    static <T> Set<T> getConstraintViolations(final Object actual) {
        try {
            @SuppressWarnings({"unchecked"})
            final Set<T> constraintViolations = (Set<T>) constraintViolationClass().getMethod("getConstraintViolations").invoke(actual);
            return constraintViolations;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private ConstraintViolationExceptionUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
