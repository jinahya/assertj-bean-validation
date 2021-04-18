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

import java.util.function.Function;

@SuppressWarnings({"java:S125"})
final class ValidationExceptionUtils {

    private static final String SUFFIX = "ValidationException";

    /**
     * Applies the class of {@code ....validation.ViolationException} to specified function and returns the result.
     *
     * @param function the function to applied with the class of {@code ....validation.ViolationException}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    static <R> R applyValidationExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return ValidationReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    /**
     * Returns the class of {@code ....validation.Path.PropertyNode}.
     *
     * @return the class of {@code ....validation.Path.PropertyNode}.
     */
    static Class<?> getValidationExceptionClass() {
        return applyValidationExceptionClass(Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of {@code ....validation.ViolationException}.
     *
     * @param object the object to be tested.
     * @return {@code true} if {@code actual} is an instance of {@code ....validation.ViolationException}; {@code false}
     * otherwise.
     */
    static boolean isNullOrInstanceOfValidationExceptionClass(final Object object) {
        if (object == null) {
            return true;
        }
        return ValidationReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ViolationException}.
     *
     * @param object the object to be tested.
     */
    static <T> T requireValidationExceptionInstance(final T object) {
        if (object == null) {
            return null;
        }
        return ValidationReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
    }

    private ValidationExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
